package com.tienda.controller;

import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "*")
public class ChatbotController {

    @PostMapping
    public Map<String, String> responder(@RequestBody Map<String, String> datos) {
        String mensaje = datos.getOrDefault("mensaje", "").toLowerCase();
        String respuesta;

        if (mensaje.contains("hola") || mensaje.contains("buenas")) {
            respuesta = "¡Hola! Bienvenido a la Tienda del Barrio. ¿Qué quieres comprar hoy? Puedes preguntarme por precios, horario o domicilio.";
        } else if (mensaje.contains("precio") || mensaje.contains("cuanto") || mensaje.contains("cafe") || mensaje.contains("arroz")) {
            respuesta = "Tenemos buenos precios de barrio: Café desde $5.000, Arroz desde $4.500. ¿Cuál producto quieres que te busque?";
        } else if (mensaje.contains("horario") || mensaje.contains("abierto") || mensaje.contains("abre")) {
            respuesta = "Estamos abiertos de Lunes a Sábado de 7:00 a.m. a 9:00 p.m. y Domingo de 8:00 a.m. a 1:00 p.m.";
        } else if (mensaje.contains("domicilio") || mensaje.contains("envio") || mensaje.contains("llevan")) {
            respuesta = "¡Claro! Hacemos domicilio en todo el barrio, gratis si es cerquita y en 20 minutos te llega. ¿Qué quieres pedir?";
        } else if (mensaje.contains("gracias")) {
            respuesta = "Con gusto, vecino. Aquí estamos pa' servirle. ¿Algo más?";
        } else if (mensaje.contains("chao") || mensaje.contains("adios")) {
            respuesta = "¡Chao! Gracias por visitar la Tienda del Barrio. Vuelve pronto.";
        } else {
            respuesta = "No te entendí muy bien, pero puedo ayudarte con: precios, horario, domicilio o qué productos tenemos. ¿Qué quieres saber?";
        }

        Map<String, String> resp = new HashMap<>();
        resp.put("respuesta", respuesta);
        return resp;
    }
}