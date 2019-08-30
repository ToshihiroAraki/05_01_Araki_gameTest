import java.util.*;
class Monster {

    String name;
    int hp;
    int attackPoint;
    int guardPoint;
    int speedPoint;
    String type;
    String[] trickList = new String[4];
    private Trick[] tricks = new Trick[4];
    private Random random = new Random();

    static Monster ofCharmander() { return new Monster(1); }
    static Monster ofSquirtle() { return new Monster(2); }
    static Monster ofBulbasaur() { return new Monster(3); }

    //初期値設定_基準値に乱数で+0～5ポイント加算させる
    private Monster(int monsterNum){
        if(monsterNum == 1){
            this.name = "ヒトカゲ";
            this.hp = 50 + new Random().nextInt(6);
            this.attackPoint = 20 + new Random().nextInt(6);
            this.guardPoint = 10 + new Random().nextInt(6);
            this.speedPoint = 13 + new Random().nextInt(6);
            this.type = "炎";
            this.tricks[0] = Trick.SCRATCH;
            this.tricks[1] = Trick.CUT;
            this.tricks[2] = Trick.FIRE;
            this.tricks[3] = Trick.FIRE2;
            for (int j=0;j<4;j++){
                this.trickList[j] = (j + 1) + ":" + this.tricks[j].getName();
            }
        }else if(monsterNum == 2){
            this.name = "ゼニガメ";
            this.hp = 50 + new Random().nextInt(6);
            this.attackPoint = 20 + new Random().nextInt(6);
            this.guardPoint = 10 + new Random().nextInt(6);
            this.speedPoint = 13 + new Random().nextInt(6);
            this.type = "水";
            this.tricks[0] = Trick.BODYATTACK;
            this.tricks[1] = Trick.CUT;
            this.tricks[2] = Trick.BUBBLE;
            this.tricks[3] = Trick.WATERGUN;
            for (int j=0;j<4;j++){
                this.trickList[j] = (j + 1) + ":" + this.tricks[j].getName();
            }
        }else if(monsterNum == 3){
            this.name = "フシギダネ";
            this.hp = 50 + new Random().nextInt(6);
            this.attackPoint = 20 + new Random().nextInt(6);
            this.guardPoint = 10 + new Random().nextInt(6);
            this.speedPoint = 13 + new Random().nextInt(6);
            this.type = "草";
            this.tricks[0] = Trick.BODYATTACK;
            this.tricks[1] = Trick.CUT;
            this.tricks[2] = Trick.VINEWHIP;
            this.tricks[3] = Trick.RAZORREAF;
            for (int j=0;j<4;j++){
                this.trickList[j] = (j + 1) + ":" + this.tricks[j].getName();
            }
        }
    }

    Trick attack(int n) {
        System.out.println(this.name + "が「" + this.tricks[n].getName() + "」を繰り出した");
        return this.tricks[n];
    }
    void giveDamage(Trick trick) {
        //命中判定
        int ranHit = this.random.nextInt(trick.getHit());
        if (ranHit == 1){
            System.out.println("しかし" + this.name + "には命中しなかった\n");
        }else{
            //クリティカル判定
            int ran = this.random.nextInt(trick.getCriticalProbability());
            int totalDamage;
            if(ran == 1){
                System.out.println("クリティカルヒット!！\nダメージ2倍!!");
                totalDamage = trick.getDamage() * 2;
            }else totalDamage = trick.getDamage();

            //属性相性判定
            if((trick.getAttribute().equals("炎") && this.type.equals("草")) ||
                    (trick.getAttribute().equals("水") && this.type.equals("炎")) ||
                    (trick.getAttribute().equals("草") && this.type.equals("水"))){
                System.out.println("こうかはばつぐんだ！!\nダメージ2倍！!");
                totalDamage = totalDamage * 2;
            }else if((trick.getAttribute().equals("草") && this.type.equals("炎")) ||
                    (trick.getAttribute().equals("炎") && this.type.equals("水")) ||
                    (trick.getAttribute().equals("水") && this.type.equals("草"))){
                System.out.println("こうかはいまひとつだ...\nダメージ半分...");
                totalDamage = totalDamage /2;
            }
            //ダメージ計算
            System.out.println(this.name + "は" + totalDamage + "のダメージを受けた");
            this.hp = Math.max(this.hp - totalDamage,0);
            System.out.println(this.name + "の残りHPは" + this.hp + "\n");
        }
    }
}
