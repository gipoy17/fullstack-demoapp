//package com.demo.miniapp.authentication;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.DependsOn;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
//import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//@DependsOn({"authenticationManagerBean"})
//@EnableAuthorizationServer
//public class OAuth2Config extends AuthorizationServerConfigurerAdapter {
//
//    private String clientId = "miniappdemo";
//        private String clientSecret = "my-secret";
//    private String privateKey = "-----BEGIN RSA PRIVATE KEY-----\n" +
//            "MIIEowIBAAKCAQEAzbsnp7VRcdADU/41FsvSdinrcepwkxb8PE1Wpcuv9I3p17zJ\n" +
//            "mmDlyhtT6I4zKZVgGS5pl8HVnYYX95pqJp6qYzYGNB1wpBC9va0nSjpyjmhl3wV+\n" +
//            "KgqUg8L046bNE0rMbVVGIpnIr7ZL8qVnrnZtib0Q3vteBV9xtnkSRsK0E8OjAXtB\n" +
//            "zVb5XvD3JoKIU4+goSgccTtOVRQe+eI+tVS1kqrBFVSlie31vX3NIQz6LPgKnIm9\n" +
//            "X4uhaRmP6oL0YqAX//KB0sSD3Isq314zQssxqiDTJ9B42j7VSJ46hY1QhVME6n0b\n" +
//            "+G10f3GYMmGGyweP0HlPQEo4fBaoaGCO/VEBNwIDAQABAoIBABg4xL52FpSzzPCC\n" +
//            "qXV5odnPrazH0WE//4qKAH2csaa1XqbLz2Chr+xPs11aAKQQtjPAxyXa6G+XbhL0\n" +
//            "1zKc06rCnZBdNvnaDrzaOhn/L6WcnQHY/7WrcTjiJzr2VIbE87SbqZpBSOOHLwHu\n" +
//            "f3qG7vacj/Pw3Gz9g8HcF07FdbYtyEyZxXvwzIT7/XLFuBtyXYW0Fj1CyfJppmFp\n" +
//            "i8JKTerL/Kg3FfWa4kvwIVEoIIOCxVrPbAXydwAmVXPqa7rA7IAV8To/1mA9tlef\n" +
//            "7C4Tu4jGMDg3FfjYPnPrMyIrt+3D37YeO4oFxiTuzUUwpedYK3ZzeoaORGqE7GCe\n" +
//            "6tcpJSECgYEA85Xcx5mqNTNGzq5cnIcxCh5ckJUM47QqlS9q7JEzTKQfHBxhffi4\n" +
//            "G7QY42CuMvK5iGu9abLU1LC8BfkIcREVaTOyf/ZiNC4tun7mkvxkQqc7D6YVh+4+\n" +
//            "9AfBW0gdgaaLk2X9Mbg/OmkKOF5YG0z5Q37259LJ7g9gKNPAZXtPyJUCgYEA2Ddl\n" +
//            "E7Ek2s4WGrVbCvJgJfgw8IsPhWV4kxyd5qaEZ9VmXuTrQKBxtS6bBIQ0QVV5RVs9\n" +
//            "b3+kdD9r0YhgHX1yjdoaigQpfXbBjN2J8ltflMA4JPhDsZ70F4bhJnXgiwFsWsID\n" +
//            "YHzrM8KhsbYc9WEwT9CMlyLkH6phEaT49r5Bk5sCgYEAyImxuTZedewgOFo+brOy\n" +
//            "gPUcGgBMG08h266tJdQo2sknv5nx/ab90/kCoHfhEprYemLRDysmo3BZa8Vp2MRK\n" +
//            "z1C7fTQYB4OZLDf7ljlqRaGuMyT2BvHzLC8SVhdaC3pU4OYPfJaGn40I1/18JgXK\n" +
//            "FXJ6zcKxcmeAMDyc4i+SML0CgYBmYRSDVoXU9XCSBP2vGUVdtT+3JqGa0IlfX1WF\n" +
//            "OaPIZsuqc0rRtycgW4kbgl4b8ZUSujlE6nO8d6td8+1PCW+Mq5GVeJMMzYaoZN5E\n" +
//            "2+9OSBZNqyCiolmCKmPCCl7gczQTZXqj3YKhZ6+YrlrX9cUiheWMSwEp3UtJR4x7\n" +
//            "iWsTsQKBgF9soq9L+gae50nGq98LgbfGtOvxQxe/y/ywyjCRy2jNFa1YkY2idLha\n" +
//            "wfgE9oN9v4Oq0JXtgm8caMG9yupm610NsHR7WWLUkQdRssgfsBkUQMKMr07os3Pf\n" +
//            "4qpAtzyzqls+RJVftmBEs/S6Q3i5ivdvdSores6aNVwVNlx9qADT\n" +
//            "-----END RSA PRIVATE KEY-----";
//    private String publicKey = "ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQDNuyentVFx0ANT/jUWy9J2Ketx6nCTFvw8TValy6/0jenXvMmaYOXKG1PojjMplWAZLmmXwdWdhhf3mmomnqpjNgY0HXCkEL29rSdKOnKOaGXfBX4qCpSDwvTjps0TSsxtVUYimcivtkvypWeudm2JvRDe+14FX3G2eRJGwrQTw6MBe0HNVvle8PcmgohTj6ChKBxxO05VFB754j61VLWSqsEVVKWJ7fW9fc0hDPos+Aqcib1fi6FpGY/qgvRioBf/8oHSxIPciyrfXjNCyzGqINMn0HjaPtVInjqFjVCFUwTqfRv4bXR/cZgyYYbLB4/QeU9ASjh8FqhoYI79UQE3 ocampojeffreyl017@gmail.com";
//
//    @Autowired
//    @Qualifier("authenticationManagerBean")
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private PasswordEncoder bCryptPasswordEncoder;
//
//    @Bean
//    public JwtAccessTokenConverter tokenEnhancer() {
//        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        converter.setSigningKey(privateKey);
//        converter.setVerifierKey(publicKey);
//        return converter;
//    }
//
//    @Bean
//    public JwtTokenStore tokenStore() {
//        return new JwtTokenStore(tokenEnhancer());
//    }
//
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
//                .accessTokenConverter(tokenEnhancer());
//    }
//
//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//        security.passwordEncoder(NoOpPasswordEncoder.getInstance()).tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
//    }
//
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.inMemory().withClient(clientId).secret(clientSecret).scopes("read", "write")
//                .authorizedGrantTypes("password", "refresh_token").accessTokenValiditySeconds(999999)
//                .refreshTokenValiditySeconds(20000);
//    }
//}
