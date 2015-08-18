mkdir /home/tomcat
chmod 777 /home/tomcat

tomcat_user=$(ps aux | grep -v grep | grep tomcat | awk ' { print $1 }')
echo "Tomcat is running as user: $tomcat_user"
chown $(whoami):$tomcat_user /home/tomcat/