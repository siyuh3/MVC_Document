package com.example.Document.Service;

import com.example.Document.Dao.DocumentDao;
import com.example.Document.Entity.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: Document
 * @description:
 * @author: Siyu Hou
 * @create: 2023-06-15 18:06
 **/
@Service
public class DocumentService {
    @Autowired
    DocumentDao dao;

    public Document getDocument(int id) {
        if (dao.getDocument(id) == null) {
            throw new RuntimeException();
        }
        return dao.getDocument(id);
    }

    public void updateDocument(int id, Document document) {
        if (dao.getDocument(id) == null) {
            throw new RuntimeException();
        }
        dao.updateDocument(id, document);
    }

    public List<Document> listDocuments() {
        return dao.listDocuments();
    }

    public Document createDocument(Document document) {
        if (dao.getDocument(document.getId()) != null) {
            throw new RuntimeException();
        }
        return dao.createDocument(document);
    }

    public void deleteDocument(int id) {
        if (dao.getDocument(id) == null) {
            throw new RuntimeException();
        }
        dao.deleteDocument(id);
    }
}
