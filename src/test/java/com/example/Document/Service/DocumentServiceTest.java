package com.example.Document.Service;

import com.example.Document.Dao.DocumentDao;
import com.example.Document.Entity.Document;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @program: Document
 * @description:
 * @author: Siyu Hou
 * @create: 2023-06-28 12:45
 **/
@SpringBootTest
public class DocumentServiceTest {
    @Autowired
    private DocumentService service;

    @MockBean
    private DocumentDao dao;

    @BeforeEach
    public void setUp() {
        System.out.println("Before the Test");
    }

    @AfterEach
    public void afterTest() {
        System.out.println("After the Test");
    }

    @Test
    public void testGetDocument() {
        Document doc = new Document();
        doc.setId(1);
        doc.setContent("Test Content");
        when(dao.findById(1)).thenReturn(Optional.of(doc));

        Document result = service.getDocument(1);
        assertEquals(doc, result);
    }

    @Test
    public void updateDocument() {
        Document document = new Document();
        document.setId(1);
        document.setContent("Original Content");
        when(dao.findById(1)).thenReturn(Optional.of(document));
        when(dao.save(any(Document.class))).thenAnswer(invocation -> invocation.getArgument(0));
        String newContent = "New Content";
        Document updatedDocument = service.updateDocument(1, newContent);
        assertEquals(newContent, updatedDocument.getContent());
        verify(dao, times(1)).findById(1);
        verify(dao, times(1)).save(any(Document.class));
    }

    @Test
    public void testListDocuments() {
        Document doc1 = new Document();
        doc1.setId(1);
        doc1.setContent("Content 1");

        Document doc2 = new Document();
        doc2.setId(2);
        doc2.setContent("Content 2");

        List<Document> documents = Arrays.asList(doc1, doc2);

        when(dao.findAll()).thenReturn(documents);

        List<Document> result = service.listDocuments();

        assertEquals(documents, result);
        verify(dao, times(1)).findAll();
    }

    @Test
    public void testCreateDocument() {
        Document document = new Document();
        document.setId(1);
        document.setContent("New Content");

        when(dao.findById(1)).thenReturn(Optional.empty());
        when(dao.save(any(Document.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Document result = service.createDocument(document);

        assertEquals(document, result);
        verify(dao, times(1)).findById(1);
        verify(dao, times(1)).save(document);
    }

    @Test
    public void testDeleteDocument() {
        Document document = new Document();
        document.setId(1);
        document.setContent("Content");

        when(dao.findById(1)).thenReturn(Optional.of(document));

        service.deleteDocument(1);

        verify(dao, times(1)).findById(1);
        verify(dao, times(1)).deleteById(1);
    }

    @Test
    public void testGetDocument_NotFound() {
        when(dao.findById(1)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            service.getDocument(1);
        });

        assertEquals("Document not found", exception.getMessage());
        verify(dao, times(1)).findById(1);
    }

    @Test
    public void testUpdateDocument_NotFound() {
        when(dao.findById(1)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            service.updateDocument(1, "New Content");
        });

        assertNull(exception.getMessage());
        verify(dao, times(1)).findById(1);
    }

    @Test
    public void testCreateDocument_AlreadyExists() {
        Document document = new Document();
        document.setId(1);
        document.setContent("Existing Content");

        when(dao.findById(1)).thenReturn(Optional.of(document));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            service.createDocument(document);
        });

        assertNull(exception.getMessage());
        verify(dao, times(1)).findById(1);
    }

    @Test
    public void testDeleteDocument_NotFound() {
        when(dao.findById(1)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            service.deleteDocument(1);
        });

        assertNull(exception.getMessage());
        verify(dao, times(1)).findById(1);
    }

}
