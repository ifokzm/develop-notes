package com.github.sailboat.common.crypto;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.sailboat.common.enums.KeyType;
import com.github.sailboat.common.enums.SignAlgorithm;
import com.github.sailboat.common.util.Hex;

public class RSAUtil {

    private static Logger log = LoggerFactory.getLogger(RSAUtil.class);

    /**
     * 生成随机密钥对（私钥，公钥）
     * 
     * @param signAlgorithm SHA256withRSA
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static KeyPair keyGenerator() throws NoSuchAlgorithmException {
        // 生成一个随机数生成器
        java.security.SecureRandom random = new java.security.SecureRandom();

        // 生成一个 RSA 密钥对
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KeyType.RSA.keyAlgo());
        keyPairGenerator.initialize(2048, random);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        // 获取私钥
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        log.info("privateKey:{}", Hex.encode(privateKey.getEncoded()));
        log.info("publicKey:{}", Hex.encode(publicKey.getEncoded()));

        return keyPair;
    }

    /**
     * 用私钥对信息生成数字签名
     *
     * @param privateKey 私钥
     * @param data 加密的数据
     * @return 签名
     */
    public static String generateSign(String privateKey, String data) {
        try {
            Signature signature = Signature.getInstance(SignAlgorithm.SHA256withRSA.getValue());
            byte[] encodedPrivateKey = Hex.decode(privateKey);

            // 使用 PKCS8 编码创建 PrivateKey 对象
            KeyFactory keyFactory = KeyFactory.getInstance(KeyType.RSA.keyAlgo());
            PrivateKey privateKeyObject = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(encodedPrivateKey));

            signature.initSign(privateKeyObject);
            signature.update(data.getBytes(StandardCharsets.UTF_8));

            return new String(Hex.encode(signature.sign()));
        } catch (NoSuchAlgorithmException | InvalidKeyException | InvalidKeySpecException | SignatureException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 用公钥检验数字签名的合法性
     *
     * @param publicKey 公钥
     * @param data 数据
     * @param sign 签名
     * @return 是否验证通过
     */
    public static boolean verifySign(String publicKey, String data, String sign) {
        try {
            Signature signature = Signature.getInstance(SignAlgorithm.SHA256withRSA.getValue());

            byte[] encodedPublicKey = Hex.decode(publicKey);

            // 使用 X.509 编码创建 PublicKey 对象
            KeyFactory keyFactory = KeyFactory.getInstance(KeyType.RSA.keyAlgo());
            PublicKey publicKeyObject = keyFactory.generatePublic(new X509EncodedKeySpec(encodedPublicKey));

            signature.initVerify(publicKeyObject);
            signature.update(data.getBytes(StandardCharsets.UTF_8));
            return signature.verify(Hex.decode(sign));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | SignatureException | InvalidKeyException e) {
            log.error(e.getMessage(), e);
        }
        return false;
    }

    public static PrivateKey privateKeyGenerator() throws NoSuchAlgorithmException, InvalidKeySpecException {
        // 生成一个随机数生成器
        java.security.SecureRandom random = new java.security.SecureRandom();

        // 生成一个 RSA 密钥对
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KeyType.RSA.keyAlgo());
        keyPairGenerator.initialize(2048, random);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        // 获取私钥
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        // 将私钥转换为 PKCS8 编码
        byte[] encodedPrivateKey = privateKey.getEncoded();

        // 使用 PKCS8 编码创建 PrivateKey 对象
        PrivateKey privateKeyObject =
            KeyFactory.getInstance(KeyType.RSA.keyAlgo()).generatePrivate(new PKCS8EncodedKeySpec(encodedPrivateKey));

        // 打印私钥
        System.out.println(privateKeyObject);

        return privateKeyObject;
    }

    public static PublicKey publicKeyGenerator() throws NoSuchAlgorithmException, InvalidKeySpecException {

        // 生成一个随机数生成器
        java.security.SecureRandom random = new java.security.SecureRandom();

        // 生成一个 RSA 密钥对
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KeyType.RSA.keyAlgo());
        keyPairGenerator.initialize(2048, random);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        // 获取公钥
        PublicKey publicKey = keyPair.getPublic();

        // 将公钥转换为 X.509 编码
        byte[] encodedPublicKey = publicKey.getEncoded();

        // 使用 X.509 编码创建 PublicKey 对象
        PublicKey publicKeyObject =
            KeyFactory.getInstance(KeyType.RSA.keyAlgo()).generatePublic(new X509EncodedKeySpec(encodedPublicKey));

        // 打印公钥
        System.out.println(publicKeyObject);
        return publicKeyObject;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        KeyPair keyPair = RSAUtil.keyGenerator();

        String privateKeyString = new String(Hex.encode(keyPair.getPrivate().getEncoded()));
        System.out.println(privateKeyString);
        String publicKeyString = new String(Hex.encode(keyPair.getPublic().getEncoded()));
        System.out.println(publicKeyString);

        String data = "hello";
        String sign = RSAUtil.generateSign(privateKeyString, data);

        System.out.println(sign);

        boolean isPass = RSAUtil.verifySign(publicKeyString, data, sign);
        System.out.println(isPass);
    }

}
