package photos.brooklyn.services;

import java.util.List;

import photos.brooklyn.images.model.Album;
import photos.brooklyn.images.model.PhotoAlbum;

public interface AlbumService {
    List<Album> getAlbums();

    PhotoAlbum getPhotoAlbum(String albumId, int imageSize, int tnSize,String authKey);
    
}
