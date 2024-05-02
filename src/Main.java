import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in);
        int userInput = 0;
        while (userInput != 7) {
            System.out.println("Conversor de monedas");
            System.out.println("1) USD => ARS");
            System.out.println("2) ARS => USD");
            System.out.println("3) USD => BRL");
            System.out.println("4) BRL => USD");
            System.out.println("5) USD => COP");
            System.out.println("6) COP => USD");
            System.out.println("7) Cierra");
            System.out.println("Elige una de las opciones");
            userInput = lectura.nextInt();

            String moneda1 = "";
            String moneda2 = "";

            if (userInput == 1) {
                moneda1 = "USD";
                moneda2 = "ARS";
            } else if (userInput == 2) {
                moneda1 = "ARS";
                moneda2 = "USD";
            } else if (userInput == 3) {
                moneda1 = "USD";
                moneda2 = "BRL";
            } else if (userInput == 4) {
                moneda1 = "BRL";
                moneda2 = "USD";
            } else if (userInput == 5) {
                moneda1 = "USD";
                moneda2 = "COP";
            } else if (userInput == 6) {
                moneda1 = "COP";
                moneda2 = "USD";
            } else if (userInput == 7) {
                break; // Salir del bucle si se elige la opción 7
            }

            System.out.println("Ingrese el valor a convertir");
            int amount = lectura.nextInt();

            String serviceUrl = "https://v6.exchangerate-api.com/v6/74a1d3ff81b1c1c0fa21dbe4/pair/" + moneda1 + "/" + moneda2 + "/" + Integer.toString(amount);

            try {
                //convertir a url
                URL url = new URL(serviceUrl);
                //abrir la  conexion
                HttpURLConnection request = (HttpURLConnection) url.openConnection();
                //Establecer conexion
                request.connect();

                // Crea una interfaz de JsonParser
                JsonParser jp = new JsonParser();
                // Obtener el contenido de la respuesta de la petición
                JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
                // Convertir el contenido a objeto JSON
                JsonObject jsonObject = root.getAsJsonObject();

                System.out.println(jsonObject.get("conversion_result").getAsString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Hasta la vista");
    }
}
