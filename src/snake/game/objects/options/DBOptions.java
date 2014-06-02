package snake.game.objects.options;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import snake.database.SQLiteConnection;

public class DBOptions implements Options{
    private Integer user_id;
    private Integer h_size;
    private Integer v_size;
    private Integer level;
    private Boolean back_sound;
    private Boolean game_sound;

    
    @Override
    public void update(int h_size, int v_size, int level, Boolean back_sound, Boolean game_sound) {
        setHSize(h_size);
        setVSize(v_size);
        setLevel(level);
        setBackSound(back_sound);
        setGameSound(back_sound);
        
        PreparedStatement updateStmt = null;
        int result = 0;

        try {

            updateStmt = SQLiteConnection.getInstance().getConnection().prepareStatement("update options set v_size = ?, h_size = ?, level = ?, back_sound = ?, game_sound = ?  where user_id = ?");

            updateStmt.setInt(1, v_size);
            updateStmt.setInt(2, h_size);
            updateStmt.setInt(3, level);
            updateStmt.setBoolean(4, back_sound);
            updateStmt.setBoolean(5, game_sound);
            updateStmt.setInt(6, 1);

            result = updateStmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DBOptions.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (updateStmt != null) {
                try {
                    updateStmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DBOptions.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public int getVSize() {
        PreparedStatement selectStmt = null;

        ResultSet rs = null;
        
        try {
            selectStmt = SQLiteConnection.getInstance().getConnection().prepareStatement("select v_size from options where user_id = ?");
            selectStmt.setInt(1, 1);

            rs = selectStmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1);
            }

            selectStmt.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DBOptions.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

                try {
                    if ( selectStmt != null ) {
                        selectStmt.close();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DBOptions.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        
        return 0;
    }

    @Override
    public int getHSize() {
        PreparedStatement selectStmt = null;

        ResultSet rs = null;
        
        try {
            selectStmt = SQLiteConnection.getInstance().getConnection().prepareStatement("select h_size from options where user_id = ?");
            selectStmt.setInt(1, 1);

            rs = selectStmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1);
            }

            selectStmt.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DBOptions.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

                try {
                    if ( selectStmt != null ) {
                        selectStmt.close();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DBOptions.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        
        return 0;
    }

    @Override
    public int getLevel() {
        PreparedStatement selectStmt = null;

        ResultSet rs = null;
        
        try {
            selectStmt = SQLiteConnection.getInstance().getConnection().prepareStatement("select level from options where user_id = ?");
            selectStmt.setInt(1, 1);

            rs = selectStmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1);
            }

            selectStmt.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DBOptions.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

                try {
                    if ( selectStmt != null ) {
                        selectStmt.close();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DBOptions.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        
        return 0;
    }

    @Override
    public boolean getBackSound() {
        PreparedStatement selectStmt = null;

        ResultSet rs = null;
        
        try {
            selectStmt = SQLiteConnection.getInstance().getConnection().prepareStatement("select back_sound from options where user_id = ?");
            selectStmt.setInt(1, 1);

            rs = selectStmt.executeQuery();
            
            if (rs.next()) {
                return rs.getBoolean(1);
            }

            selectStmt.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DBOptions.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

                try {
                    if ( selectStmt != null ) {
                        selectStmt.close();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DBOptions.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        
        return false;
    }

    @Override
    public boolean getGameSound() {
        PreparedStatement selectStmt = null;

        ResultSet rs = null;
        
        try {
            selectStmt = SQLiteConnection.getInstance().getConnection().prepareStatement("select game_sound from options where user_id = ?");
            selectStmt.setInt(1, 1);

            rs = selectStmt.executeQuery();
            
            if (rs.next()) {
                return rs.getBoolean(1);
            }

            selectStmt.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DBOptions.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

                try {
                    if ( selectStmt != null ) {
                        selectStmt.close();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DBOptions.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        
        return false;
    }

    @Override
    public void setHSize(Integer h_size) {
        this.h_size = h_size;
    }

    @Override
    public void setVSize(Integer v_size) {
        this.v_size = v_size;
    }

    @Override
    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    public void setBackSound(Boolean back_sound) {
        this.back_sound = back_sound;
    }

    @Override
    public void setGameSound(Boolean game_sound) {
        this.game_sound = game_sound;
    }
}
