// Please complete the following exercise using Java Programming Language. // Your solution should compile and execute successfully.

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Exercise {
    public static class Category {
        // Define the following fields with getters and setters:
        //    id: a unique numeric identifier of the category
        //    parentId: id of the parent category or null if it doesn't have the parent
        //    name: a string representation of category name
        private Integer id;
        private Integer parentId;
        private String name;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getParentId() {
            return parentId;
        }

        public void setParentId(Integer parentId) {
            this.parentId = parentId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }



    }

    public static void printPath(List<Category> categories) {
        // Input is an _unordered_ collection of categories, where "id", "parentId", and "name" are pre-populated.
        // Implement this method to print the full path for each category in the collection.
        //
        // For example, if category A is parent of category B and category B is parent of category C, then
        //      the path for category A is "A"
        //      the path for category B is "A > B"
        //      the path for category C is "A > B > C"
        //  where "A" is the name of category A
        //        "B" is the name of category B
        //        "C" is the name of category C
        //
        // Note: Number of categories in a specific path can be greater than 3 as provided in this example.
        //       Your solution should work with any number of parents (e.g. A > B > C > D > ... > X)
        List<Category> test=categories.stream().sorted(Comparator.comparing(Category::getId)).collect(Collectors.toList());
        findPath(test);

    }

    public  static void findPath(List<Category> categories){
        for (Category category:
                categories) {
                 String path="";
                path=stringPath(categories,category);
                String fullName=category.getName()+" > "+path;
                fullName=fullName.replaceAll(" > $", "");
                System.out.println(new StringBuilder(fullName).reverse().toString());
            }
    }


    public static String stringPath(List<Category> categories,Category category) {
        String path="";
       if(category.getParentId()==null){
            return "";
        }else{
            Category category1=categories.stream()
                    .filter(cat -> Objects.equals(cat.getId(), category.getParentId()))
                    .findAny()
                    .orElse(null);
           if(category1!=null) {path=path+category1.getName();
             path=path+" > "+stringPath(categories,category1);
             return path;
           }
        }
       return "";
    }

    public static void main(String... args) {
        // Define a collection of Category instances
        // Invoke "printPath" method above to print the path for all the categories in the collection
        List<Category> categories=new ArrayList<>();
        Category category=new Category();
        category.setId(1);
        category.setName("A");
        categories.add(category);
        category=new Category();
        category.setId(2);
        category.setName("B");
        category.setParentId(1);
        categories.add(category);

        category=new Category();
        category.setId(3);
        category.setName("C");
        category.setParentId(2);
        categories.add(category);

        category=new Category();
        category.setId(4);
        category.setName("D");
        category.setParentId(3);
        categories.add(category);

        printPath(categories);



    }
}

