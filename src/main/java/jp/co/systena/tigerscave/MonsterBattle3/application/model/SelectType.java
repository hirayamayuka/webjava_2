package jp.co.systena.tigerscave.MonsterBattle3.application.model;

public class SelectType extends Monster
{
	public SelectType(String name , String mytype , int hp , int attack , int speed , String yuuritype  , String furitype)
	{
		//super(name,hp,speed,advantageous_type,unfavorable_type);
		super.setName(name);
		super.setMytype(mytype);
		super.setHp(hp);
		super.setAttack(attack);
		super.setSpeed(speed);
		super.setYuuriType(yuuritype);
		super.setFuriType(furitype);
	}

/*	@Override
	public void attack()
	{
		System.out.println("フシギダネのアタック");
	}

*/
}

