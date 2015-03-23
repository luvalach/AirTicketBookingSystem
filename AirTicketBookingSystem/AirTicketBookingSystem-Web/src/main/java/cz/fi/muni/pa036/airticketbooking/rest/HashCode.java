/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa036.airticketbooking.rest;

/**
 *
 * @author Lukáš Valach
 */
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class HashCode {

 public static String getHashPassword(String password) {
  BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
  String hashedPassword = passwordEncoder.encode(password);

  System.out.println(hashedPassword);
  return hashedPassword;
 }

}


