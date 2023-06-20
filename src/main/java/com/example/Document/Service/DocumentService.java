package com.example.Document.Service;

import com.example.Document.Dao.DocumentDao;
import com.example.Document.Entity.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @program: Document
 * @description:
 * @author: Siyu Hou
 * @create: 2023-06-15 18:06
 **/
@Service
public class DocumentService {
    DocumentDao dao;

    @Autowired
    public DocumentService(DocumentDao dao) {
        this.dao = dao;
    }


    public Document getDocument(int id) {
        Optional<Document> optionalDocument = dao.findById(id);
        if (optionalDocument.isEmpty()) {
            throw new RuntimeException();
        }
        return optionalDocument.get();
    }

    public Document updateDocument(int id, String content) {
        Optional<Document> optionalDocument = dao.findById(id);
        if (optionalDocument.isEmpty()) {
            throw new RuntimeException();
        }
        Document doc = optionalDocument.get();
        doc.setContent(content);
        return dao.save(doc);
    }

    public List<Document> listDocuments() {
        return dao.findAll();
    }

    public Document createDocument(Document document) {
        Optional<Document> optionalDocument = dao.findById(document.getId());
        if (optionalDocument.isPresent()) {
            throw new RuntimeException();
        }
        return dao.save(document);
    }

    public void deleteDocument(int id) {
        Optional<Document> optionalDocument = dao.findById(id);
        if (optionalDocument.isEmpty()) {
            throw new RuntimeException();
        }
        dao.deleteById(id);
    }
}
