package cn.edu.whu.lmars.rsrec.entity;

/**
 * Created by IntelliJ IDEA.
 * User: gispower
 * Date: 12-2-16
 * Time: 上午10:12
 * To change this template use File | Settings | File Templates.
 */
public class SpatialReltionType {
	public enum DirectionTypeEnum {        //东，南，西，北，东北，东南，西北，西南，中部,中北，中西，中东，中南
        E,
        S,
        W,
        N,
        EN,
        ES,
        WN,
        WS,
        Center,
        CN,
        CW,
        CE,
        CS
    }

    public enum TopoRelTypeEnum {        //相交，包含，内部，穿越，重叠，相接,相等
        intersect,
        contains,
        within,
        cross,
        overlaps,
        touches,
        equals,
        disjoint,
        riverContains
    }
}
