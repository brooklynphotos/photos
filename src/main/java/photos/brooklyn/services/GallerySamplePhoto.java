package photos.brooklyn.services;

import photos.brooklyn.images.model.Photo;

public class GallerySamplePhoto extends Photo {
    private String galleryName;

    public GallerySamplePhoto() {}
    public GallerySamplePhoto(String galleryName, Photo photo) {
        this.galleryName = galleryName;
        this.setThumbnailUrl(photo.getThumbnailUrl());
        this.setTitle(photo.getTitle());
        this.setUrl(photo.getUrl());
        this.setHeight(photo.getHeight());
        this.setWidth(photo.getWidth());
        this.setThumbnailHeight(photo.getThumbnailHeight());
        this.setThumbnailWidth(photo.getThumbnailWidth());
    }

    public String getGalleryName() {
        return galleryName;
    }

    public void setGalleryName(String galleryName) {
        this.galleryName = galleryName;
    }
}
