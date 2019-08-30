public enum Trick {
    /* 変動しない項目はenumで設定しておく */
    SCRATCH("ひっかく", 10,"ノーマル",10,10),
    CUT("きりさく",15,"ノーマル",2,5),
    FIRE("ひのこ", 12,"炎",10,20),
    FIRE2("かえんほうしゃ",20,"炎",20,10),
    BODYATTACK("たいあたり",10,"ノーマル",10,10),
    BUBBLE("あわ",12,"水",10,20),
    WATERGUN("みずでっぽう",15,"水",20,10),
    VINEWHIP("つるのムチ",12,"草",10,20),
    RAZORREAF("はっぱカッター",15,"草",20,10)
    ;
    private final String name;
    private final int damage;
    private final String attribute;
    private final int criticalProbability;
    private final int hit;

    private Trick(String name, int damage, String attribute,int criticalProbability,int hit) {
        this.name = name;
        this.damage = damage;
        this.attribute = attribute;
        this.criticalProbability = criticalProbability;
        this.hit = hit;
    }

    public String getName() { return this.name; }
    public int getDamage() { return this.damage; }
    public String getAttribute(){ return this.attribute; }
    public int getCriticalProbability(){ return this.criticalProbability;}
    public int getHit(){ return this.hit; }
}
