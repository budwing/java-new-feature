package com.github.budwing.java7.permutation.crawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import com.github.budwing.java7.permutation.PermutationProcessor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PasswordProcessor implements PermutationProcessor {
    public Object process(Object[] a) throws IOException {
        URL url = new URL("http://address.to.be.tested");
        URLConnection uc = url.openConnection();
        uc.setDoOutput(true);

        try (OutputStream out = uc.getOutputStream();) {
            StringBuffer param = new StringBuffer("id=username&passwd=");
            for (Object o : a) {
                param.append(o);
            }
            out.write(param.toString().getBytes());
            uc.connect();

            BufferedReader br = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            String s;
            while ((s = br.readLine()) != null) {
                // conditions which can verify the password
                if (s.contains("some condition 1") || s.contains("some condition 2")) {
                    log.debug("the parameter is {}.", param);
                    System.exit(0);
                }
            }
        }

        return false;
    }

}
