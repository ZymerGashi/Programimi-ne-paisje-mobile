package com.example.dell.atlasi;

import java.security.MessageDigest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;


public class HashSHA256 
{
	static Connection conn=null;
	static ResultSet res=null;
	static PreparedStatement pst=null;
	
	public static void main(String[] args) 
	{
		String salt = gjeneroSalt();
		String pass = "zize123";
		String hashedValue = hash(pass, salt);
		System.out.println("Salt: "+salt+"\nPass: "+pass+"\nHashedValue: "+hashedValue);
	}
	
	/*public static void updatePass(String username, String pass)
	{
		try
		{
			conn = dbConnect.connectDb();
			MessageDigest md = MessageDigest.getInstance("SHA-256");
				
			String sql = "SELECT salt FROM profesoret WHERE Username = '" + username + "' AND Fjalekalimi = '"+pass+"'";
			pst = conn.prepareStatement(sql);
			res = pst.executeQuery();
			res.next();
			String salt = res.getString("salt");
			
			md.update((pass+salt).getBytes("UTF-8"));
			byte[] digest = md.digest();
			String saveHashedPass  = String.format("%064x", new java.math.BigInteger(1, digest));
			
			sql = "UPDATE profesoret SET Fjalekalimi='"+saveHashedPass+"' WHERE Fjalekalimi='"+pass+"'";
			pst = conn.prepareStatement(sql);
			pst.execute();
				
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}*/
	
	public static String hash(String pass, String salt)
	{
		try
		{
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			
			md.update((pass+salt).getBytes("UTF-8"));
			byte[] digest = md.digest();
			String saveHashedPass  = String.format("%064x", new java.math.BigInteger(1, digest));
			
			return saveHashedPass;
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
			return null;
		}
	}
	
	public static String gjeneroSalt()
	{
		String bashkesiaChar = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		char[] karakteret = bashkesiaChar.toCharArray();
		String salt="";
		
		Random rn = new Random();
		for(int i=0; i<10;i++)
		{
			salt+=karakteret[rn.nextInt(karakteret.length)];
		}
		return salt;
		
	}
}
