package photos.brooklyn.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import photos.brooklyn.images.model.Album;
import photos.brooklyn.images.model.Photo;
import photos.brooklyn.images.model.PhotoAlbum;

import com.google.gdata.client.photos.PicasawebService;
import com.google.gdata.data.photos.AlbumEntry;
import com.google.gdata.data.photos.AlbumFeed;
import com.google.gdata.data.photos.PhotoEntry;
import com.google.gdata.data.photos.UserFeed;
import com.google.gdata.util.ServiceException;

@Service
public class AlbumServiceImpl implements AlbumService {
    private static Logger logger = LoggerFactory.getLogger(AlbumServiceImpl.class);
    @Autowired
    private PicasawebService svc;
    
    @Value("${username}")
    private String username;
    
    @Override
    @Cacheable(value="photos", key="#albumId")
    public PhotoAlbum getPhotoAlbum(String albumId,int imageSize, int tnSize,String authKey) {
        URL feedUrl;
        try {
            feedUrl = new URL(String.format("https://picasaweb.google.com/data/feed/api/user/%s/albumid/%s?thumbsize=%d&imgmax=%d&authkey=Gv1sRg%s",username,albumId,tnSize,imageSize,authKey));
            logger.info("Retrieving data using "+feedUrl.toString());
        } catch (MalformedURLException e) {
            throw new RuntimeException("bad url",e);
        }
        AlbumFeed aFeed;
        try {
            aFeed = svc.getFeed(feedUrl, AlbumFeed.class);
        } catch (IOException e) {
            throw new RuntimeException("Connection problem for "+feedUrl,e);
        } catch (ServiceException se) {
            throw new RuntimeException("Service problem for "+feedUrl,se);
        }
        Album album = Album.newInstance(aFeed);
        List<Photo> ps = new ArrayList<>();
        for(PhotoEntry e : aFeed.getPhotoEntries()) {
            ps.add(Photo.newInstance(e));
        }
        return new PhotoAlbum(album,ps);
    }
    
    @Override
    public List<Album> getAlbums() {
        URL feedUrl;
        try {
            feedUrl = new URL(String.format("http://picasaweb.google.com/data/feed/base/user/%s?kind=album",username));
        } catch (MalformedURLException e) {
            throw new RuntimeException("bad url",e);
        }
        UserFeed gzFeed;
        try {
            gzFeed = svc.getFeed(feedUrl, UserFeed.class);
        } catch (IOException e) {
            throw new RuntimeException("Connection problem",e);
        } catch (ServiceException se) {
            throw new RuntimeException("Service problem",se);
        }
        List<Album> as = new ArrayList<>();
        for(AlbumEntry e : gzFeed.getAlbumEntries()) {
            as.add(Album.newInstance(e));
        }
        return as;
    }

}
