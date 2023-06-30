package com.example.Document.Controller;

import com.example.Document.Entity.Document;
import com.example.Document.Service.DocumentService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @program: Document
 * @description:
 * @author: Siyu Hou
 * @create: 2023-06-29 16:25
 **/
@RunWith(SpringRunner.class)
@WebMvcTest(DocumentController.class)
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public class DocumentControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DocumentService documentService;

    @Test
    public void testGetDocument() throws Exception {
        Document document = new Document();
        document.setId(1);
        document.setContent("Content");

        when(documentService.getDocument(1)).thenReturn(document);

        mvc.perform(get("/documents/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"content\":\"Content\"}"));

        verify(documentService, times(1)).getDocument(1);
    }

    @Test
    public void testListDocuments() throws Exception {
        Document document1 = new Document();
        document1.setId(1);
        document1.setContent("Content 1");

        Document document2 = new Document();
        document2.setId(2);
        document2.setContent("Content 2");

        List<Document> documents = Arrays.asList(document1, document2);

        when(documentService.listDocuments()).thenReturn(documents);

        mvc.perform(get("/documents"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"content\":\"Content 1\"}, {\"id\":2,\"content\":\"Content 2\"}]"));

        verify(documentService, times(1)).listDocuments();
    }

    @Test
    public void testCreateDocument() throws Exception {
        Document document = new Document();
        document.setId(1);
        document.setContent("Content");

        when(documentService.createDocument(any(Document.class))).thenReturn(document);

        mvc.perform(post("/documents")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"content\":\"Content\"}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"content\":\"Content\"}"));

        verify(documentService, times(1)).createDocument(any(Document.class));
    }

    @Test
    public void testUpdateDocument() throws Exception {
        Document document = new Document();
        document.setId(1);
        document.setContent("Updated Content");

        when(documentService.updateDocument(1, "Updated Content")).thenReturn(document);

        mvc.perform(put("/documents/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("Updated Content"))
                .andExpect(status().isOk())
                .andExpect(content().string("Success update"));

        verify(documentService, times(1)).updateDocument(1, "Updated Content");
    }

    @Test
    public void testDeleteDocument() throws Exception {
        doNothing().when(documentService).deleteDocument(1);

        mvc.perform(delete("/documents/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Success delete"));

        verify(documentService, times(1)).deleteDocument(1);
    }


}


