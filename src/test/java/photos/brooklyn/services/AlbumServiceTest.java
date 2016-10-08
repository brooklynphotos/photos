package photos.brooklyn.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import photos.brooklyn.images.model.Album;
import photos.brooklyn.images.model.Photo;
import photos.brooklyn.images.model.PhotoAlbum;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {
        "/spring-test.xml"
})
public class AlbumServiceTest {
    @Autowired
    private AlbumService svc;
    @Autowired
    private CacheManager cacheMan;

    @Test
    public void testAlbumList() throws Exception{
        List<Album> as = svc.getAlbums();
        assertTrue(as.size()>0);
        assertEquals(10 ,as.size());
        for(Album a : as) {
            System.out.println(a.getId());
        }
    }

    @Ignore
    public void testPhotoListOutput() throws Exception{
        String authKey = "CM790N_NqKPcrAE";
        int imageSize = 912;
        final int tnSize = 144;
        String albumId = "6033881103029939233";
        PhotoAlbum pa = svc.getPhotoAlbum(albumId, imageSize, tnSize, authKey);
        assertTrue(pa.getAlbumData().getIcon().startsWith("http"));
        List<Photo> ps = pa.getPhotos();
        assertTrue(ps.size()>0);
//        assertEquals(47 ,ps.size());
        for(Photo a : ps) {
            System.out.println("<div class=\"pict\"><img src=\""+a.getUrl()+"\"/></div>");
        }
    }

    @Test
    public void testPhotoList() throws Exception{
        String authKey = "CIn3gNrX7oOYqgE";
        int imageSize = 912;
        int tnSize= 144;
        String albumId = "6032049628115728897";
        long now = System.currentTimeMillis();
        PhotoAlbum pa1 = svc.getPhotoAlbum(albumId, imageSize, tnSize,authKey);
        long elapsed1 = System.currentTimeMillis() - now;
        now = System.currentTimeMillis();
        PhotoAlbum pa2 = svc.getPhotoAlbum(albumId+"", imageSize+0, tnSize,authKey+"");
        long elapsed2 = System.currentTimeMillis() - now;
        assertTrue(pa2.getAlbumData().getIcon().startsWith("http"));
        List<Photo> ps = pa2.getPhotos();
        assertTrue(ps.size()>0);
        assertTrue(elapsed1>elapsed2*100);
        assertEquals(pa1.getAlbumData().getIcon(),pa2.getAlbumData().getIcon());
    }

}
