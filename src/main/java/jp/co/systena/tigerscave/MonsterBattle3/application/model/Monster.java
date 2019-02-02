package jp.co.systena.tigerscave.MonsterBattle3.application.model;

public class Monster
{
	private String name; 	//なまえ
	private String mytype; //自分のタイプ
	private int hp;			//たいりょく
	private int attack;		//こうげき
	private int speed;			//すばやさ
	private String yuuritype; // 有利タイプ
	private String furitype;  // 不利タイプ

	//なまえ
	public void setName(String name)
	{
		this.name = name;
	}
	public String getName()
	{
		return this.name;
	}

	// 自分のタイプ
	public void setMytype(String mytype)
	{
		this.mytype = mytype;
	}
	public String getMytype()
	{
		return this.mytype;
	}

	//たいりょく
	public void setHp(int hp)
	{
		this.hp = hp;
	}
	public int getHp()
	{
		return this.hp;
	}

	//こうげき
	public void setAttack(int attack)
	{
		this.attack = attack;
	}
	public int getAttack()
	{
		return this.attack;
	}

	//すばやさ
	public void setSpeed(int speed)
	{
		this.speed = speed;
	}
	public int getSpeed()
	{
		return this.speed;
	}

	//有利タイプ
	public void setYuuriType(String yuuritype)
	{
		this.yuuritype = yuuritype;
	}
	public String getYuuriType()
	{
		return this.yuuritype;
	}

	//不利タイプ
	public void setFuriType(String furitype)
	{
		this.furitype = furitype;
	}
	public String getFuriType()
	{
		return this.furitype;
	}
}
