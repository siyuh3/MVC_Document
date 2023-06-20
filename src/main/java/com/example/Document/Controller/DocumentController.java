package com.example.Document.Controller;

import com.example.Document.Entity.Document;
import com.example.Document.Service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: Document
 * @description:
 * @author: Siyu Hou
 * @create: 2023-06-15 18:08
 **/
@ResponseBody
@Controller
public class DocumentController {
    @Autowired
    DocumentService service;

    @RequestMapping(value = "/documents/{id}",method = RequestMethod.GET)
    public ResponseEntity<Object> getDocument(@PathVariable int id) {
        try {
            Document getDocument =  service.getDocument(id);
            return new ResponseEntity<>(getDocument, HttpStatus.OK);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found with the ID");
        }
    }

    @PostMapping(value = "documents", consumes = "application/json")
    public ResponseEntity<Object> createDocument(@RequestBody Document document) {
        try {
            Document createDocument = service.createDocument(document);
            return new ResponseEntity<>(createDocument, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Document with that id already exists.");
        }
    }

    @PutMapping("/documents/{id}")
    public ResponseEntity<String> updateDocument(@PathVariable int id, @RequestBody String content) {
        try {
            service.updateDocument(id, content);
            return ResponseEntity.status(HttpStatus.OK).body("Success update");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found with the ID");
        }
    }

    @GetMapping("documents")
    public List<Document> listDocuments() {
        return service.listDocuments();
    }

    @DeleteMapping("documents/{id}")
    public ResponseEntity<String> deleteDocuments(@PathVariable int id) {
        try {
            service.deleteDocument(id);
            return ResponseEntity.status(HttpStatus.OK).body("Success delete");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found with the ID");
        }
    }

}
