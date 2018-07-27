package com.ysy15350.ysyutils.common.database.utils;

import com.ysy15350.ysyutils.common.database.DbUtilsXutils3;
import com.ysy15350.ysyutils.model.ImageInfo;

import java.util.Collection;
import java.util.List;

/**
 * Created by yangshiyou on 2018/3/29.
 */

public class ImageInfoUtils extends DbUtilsXutils3<ImageInfo> {


    private static ImageInfoUtils mInstance;

    private ImageInfoUtils() {

    }


    public static ImageInfoUtils getInstance() {
        if (mInstance == null) {
            mInstance = new ImageInfoUtils();
            init();
        }
        return mInstance;
    }

    @Override
    public int saveOrUpdate(ImageInfo noteInfo) throws Exception {

        if (noteInfo == null)
            return 0;

        try {
            db.saveOrUpdate(noteInfo);
        } catch (Exception ex) {
            return 0;
        }

        return 1;
    }

    @Override
    public int insert(ImageInfo noteInfo) throws Exception {
        return 0;
    }

    @Override
    public int update(ImageInfo noteInfo) throws Exception {
        return 0;
    }

    @Override
    public int delete() throws Exception {
        return 0;
    }

    @Override
    public int delete(int id) throws Exception {
        try {
            db.deleteById(ImageInfo.class, id);
            return 1;
        } catch (Exception ex) {

        }

        return 0;
    }

    @Override
    public int delete(Collection<ImageInfo> list) throws Exception {
        return 0;
    }

    @Override
    public ImageInfo getEntity(int id) throws Exception {
        return null;
    }

    @Override
    public List<ImageInfo> getList() throws Exception {

        return db.findAll(ImageInfo.class);
    }


    /**
     * 获取图片列表
     *
     * @return
     */
    public List<ImageInfo> getImageInfoByOrderNumAndType(String orderNum, int type) {

        try {
//            List<NoteInfo> noteInfos = db.selector(NoteInfo.class).where("status", "=", 0).findAll();

            List<ImageInfo> imageInfos = db.selector(ImageInfo.class).where("status", "=", 0).and("orderNum", "=", orderNum).and("type", "=", type).findAll();

            return imageInfos;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;

    }

}
