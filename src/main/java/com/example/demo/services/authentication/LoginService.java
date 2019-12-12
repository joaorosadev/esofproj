package com.example.demo.services.authentication;

import com.example.demo.models.Estudante;
import com.example.demo.models.Explicador;
import com.example.demo.services.EstudanteService;
import com.example.demo.services.ExplicadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class LoginService {
    private Map<String,String> users=new HashMap<>();

    private ExplicadorService explicadorService;
    private EstudanteService estudanteService;

    @Autowired
    public LoginService(ExplicadorService explicadorService,EstudanteService estudanteService) {
        this.explicadorService = explicadorService;
        this.estudanteService = estudanteService;
    }

    public void addUser(String username, String password){
        this.users.put(username,password);
    }

    private Optional<Explicador> isAuthenticated(String username, String password){
        Optional<Explicador> optionalExplicador=this.explicadorService.findByNome(username);
        if(optionalExplicador.isPresent()){
            Explicador explicador=optionalExplicador.get();
            if(explicador.getPassword().equals(password)){
                return optionalExplicador;
            }
        }
        return Optional.empty();
    }

    private Optional<Estudante> isAuthenticated2(String username, String password){
        Optional<Estudante> optionalEstudante = this.estudanteService.findByNome(username);
        if(optionalEstudante.isPresent()){
            Estudante estudante=optionalEstudante.get();
            if (estudante.getPassword().equals(password)) {
                return optionalEstudante;
            }
        }
        return Optional.empty();
    }

    public boolean authenticateUser(Explicador explicador ,String token){
        return this.users.get(explicador.getNome()).equals(token);
    }

    public boolean authenticateUser2(Estudante estudante ,String token){
        return this.users.get(estudante.getNome()).equals(token);
    }

    public Optional<String> generateToken(String username, String password) {
        Optional<Explicador> optionalExplicador=this.isAuthenticated(username,password);
        if(optionalExplicador.isPresent()){

            try {
                MessageDigest digest = MessageDigest.getInstance("SHA-256");

                //token generated from concatenate client username and password
                byte[] encodedHash = digest.digest((username+password).getBytes());

                String encodedHashString=bytesToHex(encodedHash);
                this.users.put(username,encodedHashString);
                return Optional.of(encodedHashString);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return Optional.empty();
    }

    public Optional<String> generateToken2(String username, String password) {
        Optional<Estudante> optionalEstudante=this.isAuthenticated2(username,password);
        if(optionalEstudante.isPresent()){

            try {
                MessageDigest digest = MessageDigest.getInstance("SHA-256");

                //token generated from concatenate client username and password
                byte[] encodedHash = digest.digest((username+password).getBytes());

                String encodedHashString=bytesToHex(encodedHash);
                this.users.put(username,encodedHashString);
                return Optional.of(encodedHashString);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return Optional.empty();
    }


    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }


    public Optional<String> generateToken(Credentials credentials) {
        return this.generateToken(credentials.getUsername(),credentials.getPassword());
    }
}
