package com.priyakdey.atlast.konfig;

import com.google.inject.Injector;
import com.priyakdey.atlast.konfig.core.KonfigBootstrap;

/**
 * @author Priyak Dey
 */
public class KonfigServer {

    public static void main(String[] args) {
        Injector injector = KonfigBootstrap.init();
    }

}
