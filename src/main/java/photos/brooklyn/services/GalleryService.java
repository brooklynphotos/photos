package photos.brooklyn.services;

import java.util.List;

import photos.brooklyn.images.model.Gallery;

public interface GalleryService {
    Gallery getPhotoAlbumsForGallery(String gallery, int imageSize, int tnSize);

    List<GallerySamplePhoto> getRandomGalleryPhotos(int photosPerGallery,int imageSize, int tnSize);

}
