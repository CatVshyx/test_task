package org.example;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {

        List<DocumentManager.Author> authors = new ArrayList<>();
        for (int i = 0; i < 4; i++){
            authors.add(DocumentManager.Author.builder().id(String.valueOf(i)).name("author "+i).build());
        }

        List<String> titles = List.of("title1","title2","title3");

        DocumentManager manager = new DocumentManager();

        String idToFind = "";
        for(int i = 0; i < 10; i++){
            DocumentManager.Document document = new DocumentManager.Document(null,titles.get(i % titles.size()),
                    "content " + i%2 ,authors.get(i % titles.size()), Instant.now());
            manager.save(document);
            System.out.println(document);

            if (i == 0){
                idToFind = document.getId();
            }
        }
        System.out.println("\nFound by id \n" + manager.findById(idToFind).get());
        manager.search(DocumentManager.
                        SearchRequest.builder().authorIds(List.of("author 1", "author 3"))
                        .containsContents(List.of("content 0", "content 1"))
                        .build())
                .forEach(doc -> System.out.println(doc));
    }
}