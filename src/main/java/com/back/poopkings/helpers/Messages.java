package com.back.poopkings.helpers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
@Getter
@Setter
@AllArgsConstructor
public class Messages {

    private List<String> sentencesPT;
    private List<String> sentencesEN;
    private List<String> sentencesES;
    private String poop;
    private String info;
    private String top;
    private String localeMessage;

    public Messages() {
        //Portuguese sentences
        sentencesPT = new ArrayList<>();
        sentencesEN = new ArrayList<>();
        sentencesES = new ArrayList<>();
        sentencesPT.add("Ah coisa linda que tu fizeste\\!");
        sentencesPT.add("Chega a ser impresionante o que sai dessa boc\\.\\.\\. desse cú");
        sentencesPT.add("A cag@r assim intopes a canalização numa semana");
        sentencesPT.add("Um, dois, três, cagamos todos a vez\\!");
        sentencesPT.add("Se cagar fosse profissão estavas milionário/a\\!");
        sentencesPT.add("Já limpaste o cú\\?");
        sentencesPT.add("Estas a cagar bem estás\\.\\.\\.");
        sentencesPT.add("Abriste alguma fabrica de merd@\\?");
        sentencesPT.add("O teu cú é um Poeta\\. Limpa la o cú e lê o poema que ele te escreveu\\.\\.\\.");
        sentencesPT.add("Pimba\\! Já perdeste 50Kg\\!");
        sentencesPT.add("Uma parte de ti foi embora\\.\\.\\. adeus\\!");
        sentencesPT.add("Feliz dia para ti que libertaste um refugiado\\!");
        sentencesPT.add("Vais ter de cerrar isso ao meio\\!");
        sentencesPT.add("O teu cú deve estar nos records do Guiness só pode\\!");
        sentencesPT.add("Obrigado por contribuires para esta nobre causa <3");
        sentencesPT.add("ÉS O MEU HEROI\\! CÁ GRANDE TORPEDO\\!");

        // English sentences
        sentencesEN.add("What a beautiful masterpiece you\\'ve just created\\!");
        sentencesEN.add("It\\'s impressive what can come out from there\\!");
        sentencesEN.add("With that poop\\, you could clog the toilet in a week\\.");
        sentencesEN.add("One\\, two\\, three\\, we all poop together\\!");
        sentencesEN.add("If pooping were a job\\, you\\'d be a millionaire\\!");
        sentencesEN.add("Did you already clean your butt\\?");
        sentencesEN.add("You\\'re pooping like a pro\\.");
        sentencesEN.add("Did you open a poop factory or what\\?");
        sentencesEN.add("Your butt is a poet\\. Clean it and read the poem it wrote\\.");
        sentencesEN.add("Bam\\! You just lost 50 kilos\\!");
        sentencesEN.add("A part of you is gone\\.\\.\\. goodbye\\!");
        sentencesEN.add("Happy day to you for setting a prisoner free\\!");
        sentencesEN.add("You\\'re going to have to split that in half to make it fit\\.");
        sentencesEN.add("Your butt must be in the Guinness World Records\\, it has to be\\.");
        sentencesEN.add("Thank you for contributing to this noble cause \\<3");
        sentencesEN.add("You\\'re my hero\\! What a big torpedo\\!");

        // Spanish sentences
        sentencesES.add("¡Qué bonita obra de arte acabas de crear\\!");
        sentencesES.add("Es impresionante lo que puede salir de ahí abajo\\!");
        sentencesES.add("Con esa cagada\\, podrías atascar el retrete en una semana\\!");
        sentencesES.add("¡Uno\\, dos\\, tres\\, todos cagamos a la vez\\!");
        sentencesES.add("¡Si cagar fuera un trabajo\\, estarías millonario/a\\!");
        sentencesES.add("¿Ya limpiaste el culo\\?");
        sentencesES.add("¡Estás cagando con maestría\\!");
        sentencesES.add("¿Abriste una fábrica de mierda o qué\\?");
        sentencesES.add("Tu culo es un poeta\\. Limpialo y lee el poema que escribió\\.");
        sentencesES.add("¡Zas\\! ¡Ya perdiste 50 kilos\\!");
        sentencesES.add("¡Una parte de ti se ha ido\\.\\.\\. ¡adiós\\!");
        sentencesES.add("¡Feliz día liberando a un prisionero\\!");
        sentencesES.add("¡Vas a tener que romperlo a la mitad para que quepa\\!");
        sentencesES.add("¡Tu trasero debe estar en el Libro Guinness\\, seguro\\!");
        sentencesES.add("¡Gracias por contribuir a esta noble causa \\<3\\!");
        sentencesES.add("¡Eres mi héroe\\! ¡Qué pedazo de torpedo\\!");
    }

    public String getLocaleMessage() {
        return "PT \\- Português \n" +
                "EN \\- English \n" +
                "ES \\- Español";
    }

    public String getPoopMessage(String locale) {
        return getRandomMessage(locale);
    }

    public String getInfoMessage(String locale) {
        String infoPT = "Olá\\! Eu sou o Poop Bot\\. O bot que conta toda a merda que voçês fazem neste grupo \\(podes estar em varios grupos aos mesmo tempo\\) \n" +
                "Basta criares um grupo com os teus amigos e adicionar como participante o Bot @PoopKings \\(procurem nos contactos que ele existe\\)\n \n" +
                "A lista de comandos é simples\\. So tens de escrever uma mensagem normal com o texto:\n" +
                "/poop ou /poop@PoopKings\\_bot \\- Adiciona \\+1 \uD83D\uDCA9 ao teu total de cagadelas \n" +
                "/top ou /top@PoopKings\\_bot\\- Devolve a classificação dos cagões deste grupo \n" +
                "/language_pt/es/en ou /language_pt/es/en@PoopKings\\_bot\\- Modifica o idioma por defeito do Bot \n" +
                "\n" +
                "Que começe a festa de \uD83D\uDCA9 \\!";

        String infoES = "¡Hola\\! Soy el Bot de la Caca\\. El bot que cuenta toda la mierda que hacéis en este grupo \\(podéis estar en varios grupos al mismo tiempo\\) \n" +
                "Sólo tenéis que crear un grupo con vuestros amigos y añadir al Bot @PoopKings como participante \\(buscadlo en vuestros contactos\\)\n \n" +
                "La lista de comandos es sencilla\\. Sólo tenéis que escribir un mensaje normal con el texto\\:\n" +
                "/poop o /poop@PoopKings\\_bot \\- Añade \\+1 \uD83D\uDCA9 a vuestro total de cacas \n" +
                "/top o /top@PoopKings\\_bot\\- Devuelve la clasificación de los cagones de este grupo \n" +
                "/language_pt/es/en o /language_pt/es/en@PoopKings\\_bot\\- Cambia el idioma por defecto del Bot \n" +
                "\n" +
                "¡Que empiece la fiesta de \uD83D\uDCA9 \\!";

        String infoEN = "Hello\\! I\\'m the Poop Bot\\. The bot that counts all the poop you guys do in this group \\(you can be in several groups at the same time\\) \n" +
                "Just create a group with your friends and add the Bot @PoopKings as a participant \\(look for it in your contacts\\)\n \n" +
                "The list of commands is simple\\. You just have to write a normal message with the text:\n" +
                "/poop or /poop@PoopKings\\_bot \\- Adds \\+1 \uD83D\uDCA9 to your total of poops \n" +
                "/top or /top@PoopKings\\_bot\\- Returns the ranking of the poopers in this group \n" +
                "/language_pt/es/en or /language_pt/es/en@PoopKings\\_bot\\- Change the Bot's language \n" +
                "\n" +
                "Let the \uD83D\uDCA9 party begin\\!";

        switch (locale) {
            case "pt" -> {
                return infoPT;
            }
            case "en" -> {
                return infoEN;
            }
            case "es" -> {
                return infoES;
            }
            default -> {
                return infoEN;
            }
        }
    }

    public String getTopMessage(String locale) {
        String topPT = "*Nossa senhora tanta merda que vai neste grupo\\! \uD83D\uDCA9 \uD83D\uDCA9 \uD83D\uDCA9\n" +
                "Vejamos como estão as \uD83C\uDFC6classificações\uD83C\uDFC6 dos cagões deste grupo: \uD83D\uDC47* \n";
        String topEN = "Dear lord\\, so much poop in this group\\! \uD83D\uDCA9 \uD83D\uDCA9 \uD83D\uDCA9\n" +
                "Let\\'s see the \uD83C\uDFC6poopers\uD83C\uDFC6 rankings: \uD83D\uDC47* \n";
        String topES = "*\\¡Madre mía cuánta mierda que va en este grupo\\! \uD83D\uDCA9 \uD83D\uDCA9 \uD83D\uDCA9\n" +
                "Veamos cómo están las \uD83C\uDFC6clasificaciones\uD83C\uDFC6 de los cagones de este grupo: \uD83D\uDC47* \n";
        switch (locale) {
            case "pt" -> {
                return topPT;
            }
            case "en" -> {
                return topEN;
            }
            case "es" -> {
                return topES;
            }
            default -> {
                return topEN;
            }
        }
    }

    public String getRandomMessage(String locale) {
        Random r = new Random();
        int low = 0;
        int high = 15;
        int result = r.nextInt(high - low) + low;
        return switch (locale) {
            case "pt" -> sentencesPT.get(result);
            case "en" -> sentencesEN.get(result);
            case "es" -> sentencesES.get(result);
            default -> sentencesEN.get(result);
        };
    }
}
