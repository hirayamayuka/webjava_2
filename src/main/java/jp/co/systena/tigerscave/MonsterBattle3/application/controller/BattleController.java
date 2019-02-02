package jp.co.systena.tigerscave.MonsterBattle3.application.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jp.co.systena.tigerscave.MonsterBattle3.application.model.SelectField;
import jp.co.systena.tigerscave.MonsterBattle3.application.model.SelectType;


@Controller // Viewあり。Viewを返却するアノテーション
public class BattleController
{
	// Sessionの定義
	@Autowired
	HttpSession session; // セッション管理

	@RequestMapping(value="/Battle", method = RequestMethod.POST) // URLとのマッピング
	public ModelAndView battle(ModelAndView mav)
	{
		SelectType selectpoke = (SelectType)session.getAttribute("SelectType");
		SelectType enemypoke = (SelectType)session.getAttribute("EnemyPoke");

		// ログ
		List<String> log = new ArrayList<String>();


		// バトル前処理
		// ポケモンのステータスを取得
		String myname = selectpoke.getName();
		String mytype = selectpoke.getMytype();
		int myhp = selectpoke.getHp();
		int myattack = selectpoke.getAttack();
		int myspeed = selectpoke.getSpeed();
		String myyuuritype = selectpoke.getYuuriType();
		String myfuritype = selectpoke.getFuriType();
		String enemyname = enemypoke.getName();
		String enemytype = enemypoke.getMytype();
		int enemyhp = enemypoke.getHp();
		int enemyattack = enemypoke.getAttack();
		int enemyspeed = enemypoke.getSpeed();
		String enemyyuuritype = enemypoke.getYuuriType();
		String enemyfuritype = enemypoke.getFuriType();

		// フィールドを設定
		Random rnd = new Random();
		int fieldnum = rnd.nextInt(3)+1;
		String yuurifield;
		String furifield;

		if(fieldnum == 1)
		{
			yuurifield = "草";
			furifield = "水";
			// 草のフィールドを作成
			SelectField grassfield = new SelectField(yuurifield,furifield);
			// セッションにフィールドをセット
			session.setAttribute("BattleField", grassfield);
			//System.out.println("fieldは：" + yuurifield);
		}
		else if (fieldnum == 2)
		{
			yuurifield = "炎";
			furifield = "草";
			// 炎のフィールドを作成
			SelectField firefield = new SelectField(yuurifield,furifield);
			// セッションにフィールドをセット
			session.setAttribute("BattleField", firefield);
			//System.out.println("fieldは：" + yuurifield);
		}
		else
		{
			yuurifield = "水";
			furifield = "炎";
			// 水のフィールドを作成
			SelectField waterfield = new SelectField(yuurifield,furifield);
			// セッションにフィールドをセット
			session.setAttribute("BattleField", waterfield);
			//System.out.println("fieldは：" + yuurifield);
		}
		log.add("バトルフィールド:　" + yuurifield);
		log.add("相手:　" + enemyname);
		log.add("   ");


		// バトル前判定(有利タイプ、不利タイプ)
		// 自分のタイプが敵の不利タイプと一致しているかどうか
		if(mytype.equals(enemyfuritype))
		{
			myattack = myattack * 2;
		}
		// 自分のタイプが敵の有利タイプと一致しているかどうか
		else if(mytype.equals(enemyyuuritype))
		{
			myattack = myattack / 2;
		}
		else
		{}

		// 敵のタイプが自分の不利タイプと一致しているか
		if(enemytype.equals(myfuritype))
		{
			enemyattack = enemyattack * 2;
		}

		// 敵のタイプが自分の有利タイプと一致しているか
		else if(enemytype.equals(myyuuritype))
		{
			enemyattack = enemyattack / 2;
		}
		else {}


		// バトル前判定
		//SelectField selectfield = (SelectField)session.getAttribute("BattleField");
		//String battle = selectfield.getField();
		if(mytype.equals(yuurifield))
		{
			myhp = myhp + 10;
		}
		else if(mytype.equals(furifield))
		{
			myhp = myhp - 10;
		}
		else {}

		if(enemytype.equals(yuurifield))
		{
			enemyhp = enemyhp + 10;
		}
		else if(enemytype.equals(furifield))
		{
			enemyhp = enemyhp - 10;
		}
		else {}

		// バトルVS
		do
		{
			// 自分先攻のとき
			if(myspeed >= enemyspeed)
			{
				// 自分の攻撃
				// 敵にこうげきする
				enemyhp = enemyhp - myattack;
				enemypoke.setHp(enemyhp);
				log.add(myname + " のこうげき");
				log.add("相手の " + enemyname + " に " + myattack + "ダメージ！");
				if(enemyhp >= 1)
				{
					log.add(" 相手の " + enemyname +  " は残りHP " + enemyhp);
					log.add("   ");
				}
				session.setAttribute("Log", log);

				// 敵のHPが無くなったとき
				if(enemyhp <= 0)
				{

					String message = "WIN！";
					session.setAttribute("message", message);
					log.add("相手の " + enemyname + " は戦闘不能");
					session.setAttribute("Log", log);
					break;
				}
				// 相手の攻撃
				else
				{
					// こうげきを受ける
					myhp = myhp - enemyattack;
					log.add("相手の " + enemyname + " のこうげき");
					log.add(myname + " に " + enemyattack + "ダメージ！");
					if(myhp >= 1)
					{
						log.add(myname + " は残りHP " + myhp);
						log.add("   ");
					}
					session.setAttribute("Log", log);

					if(myhp <= 0)
					{
						String message = "LOSE...";
						session.setAttribute("message", message);
						log.add(myname + " は戦闘不能");
						session.setAttribute("Log", log);
						break;
					}
				}
			}

			// 自分が後攻のとき
			else if(myspeed > enemyspeed)
			{
				// こうげきを受ける
				myhp = myhp - enemyattack;
				log.add("相手の " + enemyname + " のこうげき");
				log.add(myname + " に " + enemyattack + "ダメージ！");
				if(myhp >= 1)
				{
					log.add(myname + " は残りHP " + myhp);
				}
				session.setAttribute("Log", log);

				if(myhp <= 0)
				{
					String message = "LOSE...";
					session.setAttribute("message", message);
					log.add(myname + " は戦闘不能");
					session.setAttribute("Log", log);
					break;
				}
				else
				{
					// 敵にこうげきする
					enemyhp = enemyhp - myattack;
					enemypoke.setHp(enemyhp);
					log.add(myname + " のこうげき");
					log.add("相手の " + enemyname + " に " + myattack + " ダメージ！");
					if(enemyhp >= 1)
					{
						log.add("相手の " + enemyname + " は残りHP " + enemyhp);
					}
					session.setAttribute("Log", log);

					// 敵のHPが無くなったとき
					if(enemyhp <= 0)
					{
						String message = "WIN！";
						session.setAttribute("message", message);
						log.add("相手の " + enemyname + " は戦闘不能");
						session.setAttribute("Log", log);
						break;
					}
				}
			}
		}
		while(myhp >= 1 || enemyhp >= 1);

		return new ModelAndView("redirect:/MonsterBattle3");

	}
}