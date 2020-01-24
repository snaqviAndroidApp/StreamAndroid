package implement.dfw.teleprompt.core;

import java.io.Serializable;

public interface View <T extends Serializable> {
    void render (T data);
}
