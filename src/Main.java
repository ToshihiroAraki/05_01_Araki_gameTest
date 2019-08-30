import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Monster me ;
        Monster you;
        Random random = new Random();
        Scanner sc = new Scanner(System.in);
        System.out.println("ゲームスタート");
        System.out.println("あなたの名前を教えてください");
        String playerName = sc.next();
        System.out.println("ようこそ「" + playerName + "」さん");
        int selectMe;
        while(true) {
            System.out.println("たたかいの準備をします\n");
            System.out.println("モンスターを選んでください\n");
            System.out.println("1:ヒトカゲ\n2:ゼニガメ\n3:フシギダネ");
            //モンスター選択
            if (sc.hasNextInt()) {
                selectMe = sc.nextInt();
                //入力した値が1~3であることをチェック
                if (selectMe < 1 || selectMe > 3) {
                    System.out.println("1～3の整数を入力してください。\n");
                } else {
                    switch (selectMe){
                        case 1:
                            me = Monster.ofCharmander();
                            break;
                        case 2:
                            me = Monster.ofSquirtle();
                            break;
                        case 3:
                        default:
                            me = Monster.ofBulbasaur();
                            break;
                    }
                    int selectYou = random.nextInt(3) + 1;
                    switch (selectYou){
                        case 1:
                            you = Monster.ofCharmander();
                            break;
                        case 2:
                            you = Monster.ofSquirtle();
                            break;
                        case 3:
                        default:
                            you = Monster.ofBulbasaur();
                            break;
                    }
                    break;
                }
            } else {
                //整数じゃないもの(少数や文字列)が入力された時の処理
                System.out.println("整数を入力してください\n");
                sc.next();
            }
        }
        //自分ステータス
        System.out.println("あなたは" + me.name + "を選択しました");
        me.name = playerName + "の" + me.name;
        System.out.println(me.name + "のステータスは以下の通りです\n");
        System.out.println("HP:" + me.hp);
        System.out.println("こうげき：" + me.attackPoint);
        System.out.println("ぼうぎょ：" + me.guardPoint);
        System.out.println("すばやさ：" + me.speedPoint);
        System.out.println("タイプ：" + me.type + "\n");
        //敵ステータス
        System.out.println("敵は" + you.name + "を選択しました");
        you.name = "敵の" + you.name;
        System.out.println(you.name + "のステータスは以下の通りです\n");
        System.out.println("HP:" + you.hp);
        System.out.println("こうげき：" + you.attackPoint);
        System.out.println("ぼうぎょ：" + you.guardPoint);
        System.out.println("すばやさ：" + you.speedPoint);
        System.out.println("タイプ：" + you.type + "\n");

        //バトルフェイズwhileでループさせてどちらかのhpが0になったらbreakさせる
        int turn = 1;
        int selectTrick;
        System.out.println("----バトルスタート----\n");
        while(true){
            System.out.println(turn + "ターン目\n");
            System.out.println("行動を選択してください");
            for(int i=0;i < me.trickList.length;i++){
                System.out.println(me.trickList[i]);//行動選択肢表示
            }
            if (sc.hasNextInt()) {
                selectTrick = sc.nextInt();
                //入力した値が1~4であることをチェック
                if (selectTrick < 1 || selectTrick > 4) {
                    System.out.println("1～4の整数を入力してください。");
                }else{
                    //素早さによって行動順判定
                    if(me.speedPoint >= you.speedPoint){//自分先攻の時
                        meToYou(me,you,selectTrick);
                        if(you.hp == 0){
                            System.out.println(you.name + "をやっつけた");
                            System.out.println(playerName + "は勝利した");
                            break;//hpが0になったらループ終了
                        }
                        youToMe(you,me);
                        if(me.hp == 0){
                            System.out.println(me.name + "はちからつきた");
                            System.out.println(playerName + "は敗北した");
                            break;//hpが0になったらループ終了
                        }
                    }else{//敵先攻の時
                        youToMe(you,me);
                        if(me.hp == 0){
                            System.out.println(me.name + "はちからつきた");
                            System.out.println(playerName + "は敗北した");
                            break;//hpが0になったらループ終了
                        }
                        meToYou(me,you,selectTrick);
                        if(you.hp == 0){
                            System.out.println(you.name + "をやっつけた");
                            System.out.println(playerName + "は勝利した");
                            break;//hpが0になったらループ終了
                        }
                    }
                    turn += 1;
                }
            }else {
                //整数じゃないもの(少数や文字列)が入力された時の処理
                System.out.println("整数を入力してください");
                sc.next();
            }
        }
        System.out.println("ゲーム終了");
    }

    private static void meToYou(Monster me,Monster you,int selectTrick) {
        Trick myAttack = me.attack(selectTrick - 1);
        you.giveDamage(myAttack);
    }
    private static void youToMe(Monster you,Monster me) {
        Random random = new Random();
        int selectTrick2 = random.nextInt(4);
        Trick yourAttack = you.attack(selectTrick2 );
        me.giveDamage(yourAttack);
    }
}
