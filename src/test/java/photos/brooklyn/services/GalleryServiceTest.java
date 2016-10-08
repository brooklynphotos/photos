package photos.brooklyn.services;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Locale;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import photos.brooklyn.images.model.Gallery;
import photos.brooklyn.images.model.GalleryMetaData;
import photos.brooklyn.images.model.PhotoAlbum;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {
        "/spring-test.xml"
})
public class GalleryServiceTest {
    @Autowired
    private GalleryService svc;
    @Autowired
    private CacheManager cacheMan;

    @Test 
    public void testGalleryPhotoList() throws Exception{
        int imageSize = 912;
        int tnSize = 144;
        List<PhotoAlbum> albums = svc.getPhotoAlbumsForGallery("yeshi", imageSize,tnSize).getPhotoAlbums();
//        Cache c = cacheMan.getCache("photos");
//        List<?> ks = ((net.sf.ehcache.Cache)c.getNativeCache()).getKeys();
//        assertTrue(ks.size()>0);
        List<PhotoAlbum> albumsCached = svc.getPhotoAlbumsForGallery("yeshi", imageSize,tnSize).getPhotoAlbums();
        assertEquals(13,albums.size());
        assertEquals(13,albumsCached.size());
        System.out.println(albumsCached.get(0).getAlbumData().getTitle());
        assertEquals(2, albumsCached.get(0).getAlbumData().getTitle().size());
        assertEquals(imageSize,albumsCached.get(0).getPhotos().get(0).getWidth());
    }

    @Test 
    public void testGalleryData() throws Exception{
        int imageSize = 912;
        final int tnSize = 144;
        GalleryMetaData md = svc.getPhotoAlbumsForGallery("yeshi", imageSize,tnSize).getMetaData();
        assertEquals("images/DSC_9754_narrow.jpg",md.getGalleryBanner());
    }

    @Test 
    public void testLocalizedGalleryData() throws Exception{
        int imageSize = 912;
        final int tnSize = 144;
        Gallery g = svc.getPhotoAlbumsForGallery("yeshi", imageSize,tnSize);
        g.setLocale(Locale.ENGLISH);
        assertEquals("Yeshi Baby!",g.getMetaData().getLocalizedTitle());
        assertEquals("Yeshi first week",g.getPhotoAlbums().get(0).getAlbumData().getLocalizedTitle());
    }

    @Test 
    public void testRandomPhotos() throws Exception{
        int imageSize = 912;
        final int tnSize = 144;
        List<GallerySamplePhoto> rphotos = svc.getRandomGalleryPhotos(10, imageSize,tnSize);
        assertTrue(rphotos.size()>0);
        assertTrue(rphotos.size()<=12);
    }

}
