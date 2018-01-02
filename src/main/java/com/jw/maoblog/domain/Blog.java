package com.jw.maoblog.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.elasticsearch.annotations.Document;

import com.github.rjeschke.txtmark.Processor;

@Entity
@Document(indexName = "blog", type = "blog")
public class Blog implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id // primary key.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increase strategy.
    private Long id; // blog id.

    @NotEmpty(message = "the title cannot be empty.")
    @Size(min = 2, max = 50)
    @Column(nullable = false, length = 50) // the field cannot be null.
    private String title;

    @NotEmpty(message = "the summary cannot be empty.")
    @Size(min = 2, max = 300)
    @Column(nullable = false) // the field cannot be null.
    private String summary;

    @Lob  // mapping the "Long Text" type in MySQL
    @Basic(fetch = FetchType.LAZY) // lazy update
    @NotEmpty(message = "the blog message cannot be empty.")
    @Size(min = 2)
    @Column(nullable = false) // the field cannot be null.
    private String content;

    @Lob  // mapping the "Long Text" type in MySQL
    @Basic(fetch = FetchType.LAZY) // lazy update
    @NotEmpty(message = "the blog message cannot be empty.")
    @Size(min = 2)
    @Column(nullable = false) // the field cannot be null.
    private String htmlContent; // transform "md" format into "html" format.

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") // with user create the blog.
    private User user; // one blog can only belongs to one user.

    @Column(nullable = false) // the field cannot be null.
    @org.hibernate.annotations.CreationTimestamp  // create timestamp by the database.
    private Timestamp createTime;

    @Column(name = "readSize")
    private Integer readSize = 0; // number of views or visits.

    @Column(name = "commentSize")
    private Integer commentSize = 0;  // number of comments.

    @Column(name = "voteSize")
    private Integer voteSize = 0;  // number of votes.


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "blog_comment", joinColumns = @JoinColumn(name = "blog_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "comment_id", referencedColumnName = "id"))
    private List<Comment> comments; // one blog can have many comments.

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "blog_vote", joinColumns = @JoinColumn(name = "blog_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "vote_id", referencedColumnName = "id"))
    private List<Vote> votes; // one blog can have many votes.

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "catalog_id")
    private Catalog catalog; // one blog can belongs to one catalog.

    @Column(name = "tags", length = 100)
    // the tags is save in the String format, split by comma. e.g. "java,spring boot,TensorFlow"
    private String tags;  // one blog can have many tags.

    protected Blog() {
    }

    public Blog(String title, String summary, String content) {
        this.title = title;
        this.summary = summary;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        this.htmlContent = Processor.process(content); // update the html content at the same time.
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public Integer getReadSize() {
        return readSize;
    }

    public void setReadSize(Integer readSize) {
        this.readSize = readSize;
    }

    public Integer getCommentSize() {
        return commentSize;
    }

    public void setCommentSize(Integer commentSize) {
        this.commentSize = commentSize;
    }

    public Integer getVoteSize() {
        return voteSize;
    }

    public void setVoteSize(Integer voteSize) {
        this.voteSize = voteSize;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
        this.commentSize = this.comments.size();
    }

    /**
     * add the comment.
     *
     * @param comment
     */
    public void addComment(Comment comment) {
        this.comments.add(comment);
        this.commentSize = this.comments.size();
    }

    /**
     * delete the comment.
     *
     * @param commentId
     */
    public void removeComment(Long commentId) {
        for (int index = 0; index < this.comments.size(); index++) {
            if (comments.get(index).getId() == commentId) {
                this.comments.remove(index);
                break;
            }
        }

        this.commentSize = this.comments.size();
    }

    /**
     * add the vote(like).
     *
     * @param vote
     * @return
     */
    public boolean addVote(Vote vote) {
        boolean isExist = false;
        // verify if the user has already voted for the blog.
        for (int index = 0; index < this.votes.size(); index++) {
            if (this.votes.get(index).getUser().getId() == vote.getUser().getId()) {
                isExist = true;
                break;
            }
        }

        if (!isExist) {
            this.votes.add(vote);
            this.voteSize = this.votes.size();
        }

        return isExist;
    }

    /**
     * remove the vote.
     *
     * @param voteId
     */
    public void removeVote(Long voteId) {
        for (int index = 0; index < this.votes.size(); index++) {
            if (this.votes.get(index).getId() == voteId) {
                this.votes.remove(index);
                break;
            }
        }

        this.voteSize = this.votes.size();
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
        this.voteSize = this.votes.size();
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }
}
