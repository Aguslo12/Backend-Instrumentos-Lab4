package com.example.ejerciciospring.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario extends Base{

    private Boolean activo;

    private String nombreUsuario;

    private String clave;

    private String rol;

    public void encriptarClave() {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(this.clave.getBytes());
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xFF & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            this.clave = hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
