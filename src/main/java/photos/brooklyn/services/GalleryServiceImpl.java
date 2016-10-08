package photos.brooklyn.services;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import photos.brooklyn.images.model.AlbumAccess;
import photos.brooklyn.images.model.Gallery;
import photos.brooklyn.images.model.GalleryAccess;
import photos.brooklyn.images.model.PhotoAlbum;
import photos.brooklyn.images.model.SiteAccess;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class GalleryServiceImpl implements GalleryService {
    @Value("${galleryAccess.url}")
    private String galleryAccessUrl;

    @Autowired
    private AlbumService albumSvc;

    @Override
    public List<GallerySamplePhoto> getRandomGalleryPhotos(int photosPerGallery,int imageSize, int tnSize) {
        SiteAccess d = getSiteAccess();
        final List<GallerySamplePhoto> samples = new ArrayList<>();
        for(String galleryName : d.getGalleryMap().keySet()) {
            Gallery g = getPhotoAlbumsForGallery(galleryName, imageSize,tnSize);
            int galleryPhotoCount = 0;
            for(PhotoAlbum pa : g.getPhotoAlbums()) {
                if(galleryPhotoCount++>photosPerGallery) break;
                samples.add(new GallerySamplePhoto(galleryName,pa.getPhotos().get(0)));
            }
        }
        return samples;
    }
    private SiteAccess getSiteAccess() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new URL(galleryAccessUrl), SiteAccess.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read from or parse "+galleryAccessUrl,e);
        }
    }
    @Override
    public Gallery getPhotoAlbumsForGallery(String gallery,int imageSize, int tnSize) {
        SiteAccess d = getSiteAccess();
        final GalleryAccess ga = d.getGalleryMap().get(gallery);
        if(ga==null)
            throw new IllegalArgumentException("Gallery doesn't exist: "+gallery);
        List<PhotoAlbum> as = new ArrayList<>();
        for(AlbumAccess aa: ga.getAlbums()) {
            as.add(albumSvc.getPhotoAlbum(aa.getId(), imageSize,tnSize, aa.getAuthKey()));
        }
        Gallery g = new Gallery();
        g.setMetaData(ga.getMetaData());
        g.setPhotoAlbums(as);
        return g;
    }


}
