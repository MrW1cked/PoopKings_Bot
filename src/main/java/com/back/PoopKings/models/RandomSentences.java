package com.back.PoopKings.models;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
@Getter
@Setter
@AllArgsConstructor
public class RandomSentences {

    private List<String> sentences;

    public RandomSentences() {
        sentences = new ArrayList<>();
        sentences.add("Ah coisa linda que tu fizeste\\!");
        sentences.add("Chega a ser impresionante o que sai dessa boc\\.\\.\\. desse cú");
        sentences.add("A cag@r assim intopes a canalização numa semana");
        sentences.add("Um, dois, três, cagamos todos a vez\\!");
        sentences.add("Se cagar fosse profissão estavas milionário/a\\!");
        sentences.add("Já limpaste o cú\\?");
        sentences.add("Estas a cagar bem estás\\.\\.\\.");
        sentences.add("Abriste alguma fabrica de merd@\\?");
        sentences.add("O teu cú é um Poeta\\. Limpa la o cú e lê o poema que ele te escreveu\\.\\.\\.");
        sentences.add("Pimba\\! Já perdeste 50Kg\\!");
        sentences.add("Uma parte de ti foi embora\\.\\.\\. adeus\\!");
        sentences.add("Feliz dia para ti que libertaste um refugiado\\!");
        sentences.add("Vais ter de cerrar isso ao meio\\!");
        sentences.add("O teu cú deve estar nos records do Guiness só pode\\!");
        sentences.add("Obrigado por contribuires para esta nobre causa <3");
        sentences.add("ÉS O MEU HEROI\\! CÁ GRANDE TORPEDO\\!");
    }

    public String getRandomMessage() {

        Random r = new Random();
        int low = 0;
        int high = 15;
        int result = r.nextInt(high - low) + low;
        return sentences.get(result);
    }
}
