package me.alf21.textdrawsystem.content.components;

import net.gtaun.shoebill.data.Color;
import net.gtaun.shoebill.data.Vector2D;
import net.gtaun.shoebill.data.Vector3D;
import net.gtaun.shoebill.object.Player;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

/**
 * Created by Alf21 on 01.03.2016 in the project 'textdraw-system'.
 */
public class ComponentData {

	private Object data;

	public ComponentData(Object data) {
		this.data = data;
	}

	private Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public int getInt() {
		if(data instanceof Integer)
			return (int) getData();
		return 0;
	}

	public double getDouble() {
		if(data instanceof Double)
			return (double) getData();
		return 0.0;
	}

	public float getFloat() {
		if(data instanceof Float)
			return (float) getData();
		return 0.0f;
	}

	public String getString() {
		if(data instanceof String)
			return (String) getData();
		return "";
	}

	public Date getDate() {
		if(data instanceof Date)
			return (Date) getData();
		return null;
	}

	public Object getObject() {
		return getData();
	}

	public boolean getBoolean() {
		if(data instanceof Boolean)
			return (boolean) getData();
		return false;
	}

	public char getChar() {
		if(data instanceof Character)
			return (char) getData();
		return 0;
	}

	public Long getLong() {
		if(data instanceof Long)
			return (Long) getData();
		return null;
	}

	public short getShort() {
		if(data instanceof Short)
			return (Short) getData();
		return 0;
	}

	public BigInteger getBigInteger() {
		if(data instanceof BigInteger)
			return (BigInteger) getData();
		return null;
	}

	public BigDecimal getBigDecimal() {
		if(data instanceof BigDecimal)
			return (BigDecimal) getData();
		return null;
	}

	public byte getByte() {
		if(data instanceof Byte)
			return (byte) getData();
		return 0;
	}

	public ArrayList getArrayList() {
		if(data instanceof ArrayList)
			return (ArrayList) getData();
		return null;
	}

	public Array getArray() {
		if(data instanceof Array)
			return (Array) getData();
		return null;
	}

	public HashMap getHashMap() {
		if(data instanceof HashMap)
			return (HashMap) getData();
		return null;
	}

	public Vector getVector() {
		if(data instanceof Vector)
			return (Vector) getData();
		return null;
	}

	public Vector2D getVector2D() {
		if(data instanceof Vector2D)
			return (Vector2D) getData();
		return null;
	}

	public Vector3D getVector3D() {
		if(data instanceof Vector3D)
			return (Vector3D) getData();
		return null;
	}

	public Color getColor() {
		if(data instanceof Color)
			return (Color) getData();
		return null;
	}

	public Player getPlayer() {
		if(data instanceof Player)
			return (Player) getData();
		return null;
	}
}
