package photos.brooklyn.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import photos.brooklyn.images.model.Album;
import photos.brooklyn.images.model.Gallery;
import photos.brooklyn.images.model.Photo;
import photos.brooklyn.services.AlbumService;
import photos.brooklyn.services.GalleryService;

import com.google.gdata.util.ServiceException;

@Controller
@RequestMapping("/imagesvc")
public class ImageController {
    
    private static final String DATA_TAG = "data";
    private static final String SUCCESS_TAG = "success";
    private static final String ERROR_TAG = "error";
    @Autowired
    private AlbumService svc;
    @Autowired
    private GalleryService gsvc;
    @Autowired
    private View jsonView;
    
    @RequestMapping(value="/test/{name}", method = RequestMethod.GET)
    public @ResponseBody Photo getShopInJSON(@PathVariable String name) {
 
        Photo shop = new Photo();
        shop.setUrl("blah/"+name+".jpg");
        return shop;
 
    }
    
    @RequestMapping(value="/albums", method = RequestMethod.GET)
    public @ResponseBody List<Album> getAlbums() throws ServiceException{
        return svc.getAlbums();
    }
    
    @RequestMapping(value="/gallery", method = RequestMethod.GET)
    public ModelAndView getPhotoAlbumsForGallery(
            @RequestParam String galleryName, 
            @RequestParam(defaultValue="912") int imageSize,
            @RequestParam(defaultValue="144") int tnSize,
            HttpServletRequest req) {
        try {
            Gallery g = gsvc.getPhotoAlbumsForGallery(galleryName, imageSize,tnSize);
            g.setLocale(req.getLocale());
            return createSuccess(g);
        }catch(Exception e) {
            return createErrorResponse(e);
        }
    }

    @RequestMapping(value="/randomImages", method = RequestMethod.GET)
    public ModelAndView getRandomGalleryPhotos(
            @RequestParam(defaultValue="10") int photosPerGallery, 
            @RequestParam(defaultValue="912") int imageSize,
            @RequestParam(defaultValue="144") int tnSize,
            HttpServletRequest req) {
        try {
            return createSuccess(gsvc.getRandomGalleryPhotos(photosPerGallery, imageSize, tnSize));
        }catch(Exception e) {
            return createErrorResponse(e);
        }
    }

    private ModelAndView createSuccess(Object obj) {
        Map<String, ? super Object> resp = new HashMap<String, Object>();
        resp.put(DATA_TAG,obj);
        resp.put(SUCCESS_TAG, true);
        return new ModelAndView(jsonView, resp);
    }

    private ModelAndView createErrorResponse(Exception ex) {
        Map<String, ? super Object> resp = new HashMap<>();
        ErrorResponse e = new ErrorResponse(ex);
        resp.put(ERROR_TAG, e);
        resp.put(SUCCESS_TAG, false);
        return new ModelAndView(jsonView,resp);
    }
}
