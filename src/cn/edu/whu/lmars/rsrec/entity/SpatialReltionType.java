package cn.edu.whu.lmars.rsrec.entity;

/**
 * Created by IntelliJ IDEA.
 * User: gispower
 * Date: 12-2-16
 * Time: ����10:12
 * To change this template use File | Settings | File Templates.
 */
public class SpatialReltionType {
	public enum DirectionTypeEnum {        //�����ϣ������������������ϣ����������ϣ��в�,�б����������ж�������
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

    public enum TopoRelTypeEnum {        //�ཻ���������ڲ�����Խ���ص������,���
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