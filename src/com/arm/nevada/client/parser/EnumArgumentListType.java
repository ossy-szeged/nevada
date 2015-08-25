package com.arm.nevada.client.parser;

import com.arm.nevada.client.parser.EnumVectorRegisterType;


public enum EnumArgumentListType {

	_3x8B(EnumVectorRegisterType._8B, EnumVectorRegisterType._8B, EnumVectorRegisterType._8B),
	_3x16B(EnumVectorRegisterType._16B, EnumVectorRegisterType._16B, EnumVectorRegisterType._16B),
	_3x4H(EnumVectorRegisterType._4H, EnumVectorRegisterType._4H, EnumVectorRegisterType._4H),
	_3x8H(EnumVectorRegisterType._8H, EnumVectorRegisterType._8H, EnumVectorRegisterType._8H),
	_3x2S(EnumVectorRegisterType._2S, EnumVectorRegisterType._2S, EnumVectorRegisterType._2S),
	_3x4S(EnumVectorRegisterType._4S, EnumVectorRegisterType._4S, EnumVectorRegisterType._4S),
	_3x2D(EnumVectorRegisterType._2D, EnumVectorRegisterType._2D, EnumVectorRegisterType._2D),
	
	_hn1(EnumVectorRegisterType._8B, EnumVectorRegisterType._8H, EnumVectorRegisterType._8H),
	_hn2(EnumVectorRegisterType._4H, EnumVectorRegisterType._4S, EnumVectorRegisterType._4S),
	_hn3(EnumVectorRegisterType._2S, EnumVectorRegisterType._2D, EnumVectorRegisterType._2D),
	_hn1sp(EnumVectorRegisterType._16B, EnumVectorRegisterType._8H, EnumVectorRegisterType._8H),
	_hn2sp(EnumVectorRegisterType._8H, EnumVectorRegisterType._4S, EnumVectorRegisterType._4S),
	_hn3sp(EnumVectorRegisterType._4S, EnumVectorRegisterType._2D, EnumVectorRegisterType._2D),

	_l1(EnumVectorRegisterType._8H, EnumVectorRegisterType._8B, EnumVectorRegisterType._8B),
	_l2(EnumVectorRegisterType._4S, EnumVectorRegisterType._4H, EnumVectorRegisterType._4H),
	_l3(EnumVectorRegisterType._2D, EnumVectorRegisterType._2S, EnumVectorRegisterType._2S),
	_l1sp(EnumVectorRegisterType._8H, EnumVectorRegisterType._16B, EnumVectorRegisterType._16B),
	_l2sp(EnumVectorRegisterType._4S, EnumVectorRegisterType._8H, EnumVectorRegisterType._8H),
	_l3sp(EnumVectorRegisterType._2D, EnumVectorRegisterType._4S, EnumVectorRegisterType._4S),

	_w1(EnumVectorRegisterType._8H, EnumVectorRegisterType._8H, EnumVectorRegisterType._8B),
	_w2(EnumVectorRegisterType._4S, EnumVectorRegisterType._4S, EnumVectorRegisterType._4H),
	_w3(EnumVectorRegisterType._2D, EnumVectorRegisterType._2D, EnumVectorRegisterType._2S),
	_w1sp(EnumVectorRegisterType._8H, EnumVectorRegisterType._8H, EnumVectorRegisterType._16B),
	_w2sp(EnumVectorRegisterType._4S, EnumVectorRegisterType._4S, EnumVectorRegisterType._8H),
	_w3sp(EnumVectorRegisterType._2D, EnumVectorRegisterType._2D, EnumVectorRegisterType._4S),

	_lp1(EnumVectorRegisterType._4H, EnumVectorRegisterType._8B),
	_lp2(EnumVectorRegisterType._8H, EnumVectorRegisterType._16B),
	_lp3(EnumVectorRegisterType._2S, EnumVectorRegisterType._4H),
	_lp4(EnumVectorRegisterType._4S, EnumVectorRegisterType._8H),
	_lp5(EnumVectorRegisterType._1D, EnumVectorRegisterType._2S),
	_lp6(EnumVectorRegisterType._2D, EnumVectorRegisterType._4S),

	_2x8B(EnumVectorRegisterType._8B, EnumVectorRegisterType._8B),
	_2x16B(EnumVectorRegisterType._16B, EnumVectorRegisterType._16B);

	
	public static final EnumArgumentListType[] threeSameVectorRegisterBHSD = {_3x8B, _3x16B, _3x4H, _3x8H, _3x2S, _3x4S, _3x2D };
	public static final EnumArgumentListType[] threeSameVectorRegisterBHS = {_3x8B, _3x16B, _3x4H, _3x8H, _3x2S, _3x4S };
    public static final EnumArgumentListType[] threeSameVectorRegisterFloat = {_3x2S, _3x4S, _3x2D };
    public static final EnumArgumentListType[] validArgumentsForHN = {_hn1, _hn2, _hn3 };
    public static final EnumArgumentListType[] validArgumentsForHNSP = {_hn1sp, _hn2sp, _hn3sp };
    public static final EnumArgumentListType[] validArgumentsForL = {_l1, _l2, _l3 };
    public static final EnumArgumentListType[] validArgumentsForLSP = {_l1sp, _l2sp, _l3sp };
    public static final EnumArgumentListType[] validArgumentsForW = {_w1, _w2, _w3 };
    public static final EnumArgumentListType[] validArgumentsForWSP = {_w1sp, _w2sp, _w3sp };
    public static final EnumArgumentListType[] validArgumentsForLP = {_lp1, _lp2, _lp3, _lp4, _lp5, _lp6 };
    public static final EnumArgumentListType[] validArgumentsForLogicalInstructions = {_3x8B, _3x16B};
    
	private final EnumVectorRegisterType[] args;
	private EnumArgumentListType(EnumVectorRegisterType... args){
		this.args = args;
	}
	
	public EnumVectorRegisterType[] getList() {
		return args;
	}
}
