FROM java

RUN mkdir /ambry

ADD releng/com.github.ambry.frontend.product/target/products/com.github.ambry.frontend.product/linux/gtk/x86_64 /ambry/ambry-frontend
ADD releng/com.github.ambry.server.product/target/products/com.github.ambry.server.product/linux/gtk/x86_64 /ambry/ambry-server
ADD config /ambry/config
ADD ambry.sh /ambry/ambry.sh

WORKDIR /ambry

VOLUME ["/ambry/files"]

CMD ["/ambry/ambry.sh"]

EXPOSE 1174
