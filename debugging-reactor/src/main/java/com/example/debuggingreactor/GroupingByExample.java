package com.example.debuggingreactor;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

public class GroupingByExample {

    public static void main(String[] args) {
        List<BlogPost> posts = List.of(new BlogPost("learn Java","Krishna", BlogPostType.REVIEW, 3),
                new BlogPost("learn Java 8 ","Krishna 2", BlogPostType.NEWS, 5),
                new BlogPost("learn Java 9 ","Krishna 3", BlogPostType.REVIEW, 3),
                new BlogPost("learn Java 11","Krishna", BlogPostType.GUIDE, 3)
                );

        Map<BlogPostType, List<BlogPost>> postsPerType = posts.stream().collect(groupingBy(BlogPost::getType));
        System.out.println(postsPerType);

//        posts.stream()
//                .collect(groupingBy(post -> new ImmutablePair<>(post.getType(), post.getAuthor())));


        Map<String, Integer> scores = new HashMap<>();

        scores.put("Jack", 12);
        scores.put("Jill", 15);
        scores.put("Tom", 11);
        scores.put("Darla", 15);
        scores.put("Nick", 15);
        scores.put("Nancy", 11);

        Map<Integer, List<String>> integerListMap = scores.keySet().stream().collect(groupingBy(scores::get));
        System.out.println(integerListMap);

    }

}

@Data
@AllArgsConstructor
class BlogPost{
    private String title;
    private String author;
    private BlogPostType type;
    private int likes;
//    record AuthPostTypesLikes(String author, BlogPostType type, int likes) {};
}

enum BlogPostType {
    NEWS,
    REVIEW,
    GUIDE
}
