package com.example.Document.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 * @program: Document
 * @description:
 * @author: Siyu Hou
 * @create: 2023-06-15 18:02
 **/
@Getter
@Setter
@Entity
public class Document {
    @Id
    Integer id;
    String content;
}
