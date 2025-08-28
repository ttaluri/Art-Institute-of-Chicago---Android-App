package org.ttaluri.artinstituteapiapp;

public class Artwork {
    private String title;
    private String dateDisplay;
    private String artist;
    private String artistDetails;
    private String mediumDisplay;
    private String artworkTypeTitle;
    private String imageId;
    private String dimensions;
    private String departmentTitle;
    private String creditLine;
    private String placeOfOrigin;
    private String galleryTitle;
    private String galleryId;
    private String apiLink;
    private String thumbnailUrl;
    private String fullImageUrl;


    public Artwork(String title, String dateDisplay, String artist, String artistDetails,
                   String mediumDisplay, String artworkTypeTitle, String imageId,
                   String dimensions, String departmentTitle, String creditLine,
                   String placeOfOrigin, String galleryTitle, String galleryId,
                   String apiLink, String url) {
        this.title = title;
        this.dateDisplay = dateDisplay;
        this.artist = artist;
        this.artistDetails = artistDetails;
        this.mediumDisplay = mediumDisplay;
        this.artworkTypeTitle = artworkTypeTitle;
        this.imageId = imageId;
        this.dimensions = dimensions;
        this.departmentTitle = departmentTitle;
        this.creditLine = creditLine;
        this.placeOfOrigin = placeOfOrigin;
        this.galleryTitle = galleryTitle;
        this.galleryId = galleryId;
        this.apiLink = apiLink;


        String baseUrl = "https://www.artic.edu/iiif/2/";
        this.thumbnailUrl = baseUrl + imageId + "/full/200,/0/default.jpg";
        this.fullImageUrl = baseUrl + imageId + "/full/843,/0/default.jpg";
    }


    public String getTitle() {
        return title;
    }
    public String getDateDisplay() {
        return dateDisplay;
    }
    public String getArtist() {
        return artist; }
    public String getArtistDetails() {
        return artistDetails;
    }
    public String getMediumDisplay() {
        return mediumDisplay;
    }
    public String getArtworkTypeTitle() {
        return artworkTypeTitle;
    }
    public String getImageId() {
        return imageId;
    }
    public String getDimensions() {
        return dimensions;
    }
    public String getDepartmentTitle() {
        return departmentTitle;
    }
    public String getCreditLine() {
        return creditLine;
    }
    public String getPlaceOfOrigin() {
        return placeOfOrigin;
    }
    public String getGalleryTitle() {
        return galleryTitle;
    }
    public String getGalleryId() {
        return galleryId;
    }
    public String getApiLink() {
        return apiLink;
    }
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }
    public String getFullImageUrl() {
        return fullImageUrl;
    }


}
