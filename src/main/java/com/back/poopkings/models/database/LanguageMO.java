package com.back.poopkings.models.database;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "LANGUAGE")
public class LanguageMO {

    @Id
    @Column(name = "CHAT_ID")
    private Long chatId;

    @Column(name = "LANGUAGE")
    private String language;

    @Column(name = "GROUP_NAME")
    private String groupName;

}
