import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Article {
    private String title;
    private String content;
    private String author;

    public void editContent(String newContent){
        this.content = newContent;
    }

    public void changeAuthor(String author){
        this.author = author;
    }

    public void renameTitle(String title){
        this.title = title;
    }


    @Override
    public String toString() {
        return this.title + " - " + this.content + ": " + this.author;
    }

}
