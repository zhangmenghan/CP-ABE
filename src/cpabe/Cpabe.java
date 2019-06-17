package cpabe;
import it.unisa.dia.gas.jpbc.Element;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import login_gui.Fault;
import login_gui.Success;
import cpabe.policy.LangPolicy;
import bswabe.Bswabe;
import bswabe.BswabeCph;
import bswabe.BswabeCphKey;
import bswabe.BswabeElementBoolean;
import bswabe.BswabeMsk;
import bswabe.BswabePrv;
import bswabe.BswabePub;
import bswabe.SerializeUtils;

public class Cpabe {

	/**
	 * Purpose:used to initialize the PK and MK
	 * 
	 * @param pubfile - FilePathName used to store PK
	 * @param mskfile - FilePathName used to store MK
	 * @return
	 */

	public void setup(String pubfile, String mskfile) throws IOException,
			ClassNotFoundException {
		byte[] pub_byte, msk_byte;
		BswabePub pub = new BswabePub();
		BswabeMsk msk = new BswabeMsk();
		Bswabe.setup(pub, msk);

		/* store BswabePub into mskfile */
		pub_byte = SerializeUtils.serializeBswabePub(pub);
		Common.spitFile(pubfile, pub_byte);

		/* store BswabeMsk into mskfile */
		msk_byte = SerializeUtils.serializeBswabeMsk(msk);
		Common.spitFile(mskfile, msk_byte);
	}

	/**
	 * Purpose:used to generate staff's SK when system call it.
	 * 
	 * @param pubfile 
	 * @param mskfile
	 * @param prvfile - FilePathName used to store staff's SK
	 * @param attr_str - Attibute string made from staff's attributes according to certain rule;
	 * */
	public void keygen(String pubfile, String prvfile, String mskfile,
			String attr_str) throws NoSuchAlgorithmException, IOException {
		BswabePub pub;
		BswabeMsk msk;
		byte[] pub_byte, msk_byte, prv_byte;

		/* get BswabePub from pubfile */
		pub_byte = Common.suckFile(pubfile);
		pub = SerializeUtils.unserializeBswabePub(pub_byte);

		/* get BswabeMsk from mskfile */
		msk_byte = Common.suckFile(mskfile);
		msk = SerializeUtils.unserializeBswabeMsk(pub, msk_byte);

		String[] attr_arr = LangPolicy.parseAttribute(attr_str);
		BswabePrv prv = Bswabe.keygen(pub, msk, attr_arr);

		/* store BswabePrv into prvfile */
		prv_byte = SerializeUtils.serializeBswabePrv(prv);
		Common.spitFile(prvfile, prv_byte);
	}

	/**
	 * Purpose:encrypt the plaintext with PK and staff's SK when system call it.
	 * 
	 * @param pubfile
	 * @param policy - staff must specify the access policy which system used to
	 * 					set access permissions when call it.
	 * @param inputfile - FilePathName specifying the plaintext needing to encrypt.
	 * @param encfile - FilePathName used to store the ciphertext after encrypting.
	 * */
	public void enc(String pubfile, String policy, String inputfile,
			String encfile) throws Exception {
		BswabePub pub;
		BswabeCph cph;
		BswabeCphKey keyCph;
		byte[] plt;
		byte[] cphBuf;
		byte[] aesBuf;
		byte[] pub_byte;
		Element m;

		/* get BswabePub from pubfile */
		pub_byte = Common.suckFile(pubfile);
		pub = SerializeUtils.unserializeBswabePub(pub_byte);

		keyCph = Bswabe.enc(pub, policy);
		cph = keyCph.cph;
		m = keyCph.key;
//		System.err.println("m = " + m.toString());

		if (cph == null) {
//			System.out.println("Error happed in enc");
			System.exit(0);
		}

		cphBuf = SerializeUtils.bswabeCphSerialize(cph);

		/* read file to encrypted */
		plt = Common.suckFile(inputfile);
		aesBuf = AESCoder.encrypt(m.toBytes(), plt);
		// PrintArr("element: ", m.toBytes());
		Common.writeCpabeFile(encfile, cphBuf, aesBuf);
	}

	
	/**
	 * Purpose:decrypt the ciphertext to plaintext 
	 * 			with PK and staff's SK
	 * 
	 * @param pubfile
	 * @param prvfile
	 * @param encfile
	 * @param decfile - FilePathName used to store plaintext after decrypting.
	 * */
	public void dec(String pubfile, String prvfile, String encfile,
			String decfile) throws Exception {
		byte[] aesBuf, cphBuf;
		byte[] plt;
		byte[] prv_byte;
		byte[] pub_byte;
		byte[][] tmp;
		BswabeCph cph;
		BswabePrv prv;
		BswabePub pub;

		/* get BswabePub from pubfile */
		pub_byte = Common.suckFile(pubfile);
		pub = SerializeUtils.unserializeBswabePub(pub_byte);

		/* read ciphertext */
		tmp = Common.readCpabeFile(encfile);
		aesBuf = tmp[0];
		cphBuf = tmp[1];
		cph = SerializeUtils.bswabeCphUnserialize(pub, cphBuf);

		/* get BswabePrv form prvfile */
		prv_byte = Common.suckFile(prvfile);
		prv = SerializeUtils.unserializeBswabePrv(pub, prv_byte);

		BswabeElementBoolean beb = Bswabe.dec(pub, prv, cph);
		
		
		//�Լ���ӵ�
		if(beb.e != null){
//			System.err.println("e = " + beb.e.toString());
		}
		
		if (beb.b) {
			plt = AESCoder.decrypt(beb.e.toBytes(), aesBuf);
			Common.spitFile(decfile, plt);
			new Success("密文解密成功！");
		} else {
//			System.exit(0);
			new Fault("密文解密失败，密文已被销毁!"); 
		}
	}

}
