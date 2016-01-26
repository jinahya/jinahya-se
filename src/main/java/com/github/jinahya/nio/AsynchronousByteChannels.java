/*
 * Copyright 2016 Jin Kwon &lt;onacit at gmail.com&gt;.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.jinahya.nio;


import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousByteChannel;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;


/**
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 */
public final class AsynchronousByteChannels {


    public static <A> void read(
        final AsynchronousByteChannel src,
        final ByteBuffer dst, final A attachment,
        final BiConsumer<Integer, A> completionConsumer,
        final BiConsumer<Throwable, A> failureConsumer) {

        src.read(dst, attachment, new CompletionHandler<Integer, A>() {

                 @Override
                 public void completed(Integer result, A attachment) {
                     completionConsumer.accept(result, attachment);
                 }


                 @Override
                 public void failed(Throwable exc, A attachment) {
                     failureConsumer.accept(exc, attachment);
                 }

             });
    }


    public static <A> void write(
        final AsynchronousByteChannel dst,
        final ByteBuffer src, final A attachment,
        final BiConsumer<Integer, A> completionConsumer,
        final BiConsumer<Throwable, A> failureConsumer) {

        dst.write(src, attachment, new CompletionHandler<Integer, A>() {

                  @Override
                  public void completed(Integer result, A attachment) {
                      completionConsumer.accept(result, attachment);
                  }


                  @Override
                  public void failed(Throwable exc, A attachment) {
                      failureConsumer.accept(exc, attachment);
                  }

              });
    }


    public static <A> void read(
        final AsynchronousFileChannel src,
        final ByteBuffer dst, final long position, final A attachment,
        final BiConsumer<Integer, A> completionConsumer,
        final BiConsumer<Throwable, A> failureConsumer) {

        src.read(dst, position, attachment,
                 new CompletionHandler<Integer, A>() {

                 @Override
                 public void completed(Integer result, A attachment) {
                     completionConsumer.accept(result, attachment);
                 }


                 @Override
                 public void failed(Throwable exc, A attachment) {
                     failureConsumer.accept(exc, attachment);
                 }

             });
    }


    public static <A> void write(
        final AsynchronousFileChannel dst,
        final ByteBuffer src, final long position, final A attachment,
        final BiConsumer<Integer, A> completionConsumer,
        final BiConsumer<Throwable, A> failureConsumer) {

        dst.write(src, position, attachment,
                  new CompletionHandler<Integer, A>() {

                  @Override
                  public void completed(Integer result, A attachment) {
                      completionConsumer.accept(result, attachment);
                  }


                  @Override
                  public void failed(Throwable exc, A attachment) {
                      failureConsumer.accept(exc, attachment);
                  }

              });
    }


    public static <A> void accept(
        final AsynchronousServerSocketChannel server,
        final A attachment,
        final BiConsumer<AsynchronousSocketChannel, A> completionConsumer,
        final BiConsumer<Throwable, A> failureConsumer) {

        server.accept(attachment,
                      new CompletionHandler<AsynchronousSocketChannel, A>() {

                      @Override
                      public void completed(AsynchronousSocketChannel result,
                                            A attachment) {
                          completionConsumer.accept(result, attachment);
                      }


                      @Override
                      public void failed(Throwable exc, A attachment) {
                          failureConsumer.accept(exc, attachment);
                      }

                  });
    }


    public static <A> void connect(
        final AsynchronousSocketChannel client, final SocketAddress remote,
        final A attachment, final BiConsumer<Void, A> completionConsumer,
        final BiConsumer<Throwable, A> failureConsumer) {

        client.connect(remote, attachment, new CompletionHandler<Void, A>() {

                       @Override
                       public void completed(Void result, A attachment) {
                           completionConsumer.accept(result, attachment);
                       }


                       @Override
                       public void failed(Throwable exc, A attachment) {
                           failureConsumer.accept(exc, attachment);
                       }

                   });
    }


    public static <A> void read(
        final AsynchronousSocketChannel src, final ByteBuffer[] dsts,
        final int offset, final int length, final long timeout,
        final TimeUnit unit, final A attachment,
        final BiConsumer<Long, A> completionConsumer,
        final BiConsumer<Throwable, A> failureConsumer) {

        src.read(dsts, offset, length, timeout, unit, attachment,
                 new CompletionHandler<Long, A>() {

                 @Override
                 public void completed(Long result, A attachment) {
                     completionConsumer.accept(result, attachment);
                 }


                 @Override
                 public void failed(Throwable exc, A attachment) {
                     failureConsumer.accept(exc, attachment);
                 }

             });
    }


    public static <A> void read(
        final AsynchronousSocketChannel src, final ByteBuffer dst,
        final A attachment, final BiConsumer<Integer, A> completionConsumer,
        final BiConsumer<Throwable, A> failureConsumer) {

        src.read(dst, attachment, new CompletionHandler<Integer, A>() {

                 @Override
                 public void completed(Integer result, A attachment) {
                     completionConsumer.accept(result, attachment);
                 }


                 @Override
                 public void failed(Throwable exc, A attachment) {
                     failureConsumer.accept(exc, attachment);
                 }

             });
    }


    public static <A> void read(
        final AsynchronousSocketChannel src, final ByteBuffer dst,
        final long timeout, final TimeUnit unit, final A attachment,
        final BiConsumer<Integer, A> completionConsumer,
        final BiConsumer<Throwable, A> failureConsumer) {

        src.read(dst, timeout, unit, attachment,
                 new CompletionHandler<Integer, A>() {

                 @Override
                 public void completed(Integer result, A attachment) {
                     completionConsumer.accept(result, attachment);
                 }


                 @Override
                 public void failed(Throwable exc, A attachment) {
                     failureConsumer.accept(exc, attachment);
                 }

             });
    }


    public static <A> void write(
        final AsynchronousSocketChannel dst, final ByteBuffer[] srcs,
        final int offset, final int length, final long timeout,
        final TimeUnit unit, final A attachment,
        final BiConsumer<Long, A> completionConsumer,
        final BiConsumer<Throwable, A> failureConsumer) {

        dst.write(srcs, offset, length, timeout, unit, attachment,
                  new CompletionHandler<Long, A>() {

                  @Override
                  public void completed(Long result, A attachment) {
                      completionConsumer.accept(result, attachment);
                  }


                  @Override
                  public void failed(Throwable exc, A attachment) {
                      failureConsumer.accept(exc, attachment);
                  }

              });
    }


    public static <A> void write(
        final AsynchronousSocketChannel dst, final ByteBuffer src,
        final A attachment, final BiConsumer<Integer, A> completionConsumer,
        final BiConsumer<Throwable, A> failureConsumer) {

        dst.write(src, attachment, new CompletionHandler<Integer, A>() {

                  @Override
                  public void completed(Integer result, A attachment) {
                      completionConsumer.accept(result, attachment);
                  }


                  @Override
                  public void failed(Throwable exc, A attachment) {
                      failureConsumer.accept(exc, attachment);
                  }

              });
    }


    public static <A> void write(
        final AsynchronousSocketChannel dst, final ByteBuffer src,
        final long timeout, final TimeUnit unit, final A attachment,
        final BiConsumer<Integer, A> completionConsumer,
        final BiConsumer<Throwable, A> failureConsumer) {

        dst.write(src, timeout, unit, attachment,
                  new CompletionHandler<Integer, A>() {

                  @Override
                  public void completed(Integer result, A attachment) {
                      completionConsumer.accept(result, attachment);
                  }


                  @Override
                  public void failed(Throwable exc, A attachment) {
                      failureConsumer.accept(exc, attachment);
                  }

              });
    }


    private AsynchronousByteChannels() {

        super();
    }


}

