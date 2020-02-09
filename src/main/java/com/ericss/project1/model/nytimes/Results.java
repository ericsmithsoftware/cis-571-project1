package com.ericss.project1.model.nytimes;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "list_name_encoded",
       // "bestsellers_date",
        "books"
})
public class Results {

    @JsonProperty("list_name_encoded")
    private String listNameEncoded;
  /*  @JsonProperty("bestsellers_date")
    private String bestsellersDate;*/
    @JsonProperty("books")
    private List<Book> books = null;
   /* @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();*/

    @JsonProperty("list_name_encoded")
    public String getListNameEncoded() {
        return listNameEncoded;
    }

    @JsonProperty("list_name_encoded")
    public void setListNameEncoded(String listNameEncoded) {
        this.listNameEncoded = listNameEncoded;
    }

   /* @JsonProperty("bestsellers_date")
    public String getBestsellersDate() {
        return bestsellersDate;
    }

    @JsonProperty("bestsellers_date")
    public void setBestsellersDate(String bestsellersDate) {
        this.bestsellersDate = bestsellersDate;
    }
*/
    @JsonProperty("books")
    public List<Book> getBooks() {
        return books;
    }

    @JsonProperty("books")
    public void setBooks(List<Book> books) {
        this.books = books;
    }

  /*  @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
*/
    @Override
    public String toString() {
        return "Results{" +
                "listNameEncoded='" + listNameEncoded + '\'' +
                //", bestsellersDate='" + bestsellersDate + '\'' +
                ", books=" + books +
                //", additionalProperties=" + additionalProperties +
                '}';
    }
}