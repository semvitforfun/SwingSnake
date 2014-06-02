
package snake.game.objects.options;


public interface Options {
    void setHSize(Integer v);
    void setVSize(Integer v);
    void setLevel(Integer v);
    void setBackSound(Boolean v);
    void setGameSound(Boolean v);
    
    int getVSize();
    int getHSize();
    int getLevel();
    boolean getBackSound();
    boolean getGameSound();
    
    void update(int h_size, int v_size, int level, Boolean back_sound, Boolean game_sound);
}
