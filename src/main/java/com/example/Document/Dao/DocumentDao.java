package com.example.Document.Dao;

import com.example.Document.Entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;



/**
 * @program: Document
 * @description:
 * @author: Siyu Hou
 * @create: 2023-06-15 18:25
 **/
public interface DocumentDao extends JpaRepository<Document, Integer> {
}

//@Repository
//public class DocumentDao {
//    Map<Integer, Document> documents = new HashMap<>();
//
//    public Document getDocument(int id) {
//        return documents.get(id);
//    }
//
//    public void updateDocument(int id, Document document) {
//        documents.put(id, document);
//    }
//
//    public List<Document> listDocuments() {
//        return documents.values().stream().toList();
//    }
//
//    public Document createDocument(Document document) {
//        documents.put(document.getId(), document);
//        return document;
//    }
//
//    public void deleteDocument(int id) {
//        documents.remove(id);
//    }
//}
