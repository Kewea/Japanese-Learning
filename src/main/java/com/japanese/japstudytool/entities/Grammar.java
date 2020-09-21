package com.japanese.japstudytool.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Document("grammar")
@Data
public class Grammar {
    @Id
    private String id;

    @NotBlank(message = "Title should not be blank")
    private String title;

    @NotBlank(message = "Grammar name should not be blank")
    private String name;

    @NotBlank(message = "Explanation cannot be blank")
    private String explanation;

    private String[] tag;

    private String[] similarGrammar;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "en_JP")
    private Date createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "en_JP")
    private Date updatedAt;

    @Version
    private Long version;
}
