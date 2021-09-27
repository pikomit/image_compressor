#
# To learn more about a Podspec see http://guides.cocoapods.org/syntax/podspec.html
#
Pod::Spec.new do |s|
  s.name             = 'image_compressor'
  s.version          = '1.0.0'
  s.summary          = 'Compress & Format images (jpeg, png)'
  s.description      = <<-DESC
	Compress & Format images (jpeg, png)
                       DESC
  s.homepage         = 'https://github.com/pikomit/video_compressor#readme'
  s.license          = { :file => '../LICENSE' }
  s.author           = { 'Pikomit' => 'pikomit.inc@gmail.com' }
  s.source           = { :path => '.' }
  s.source_files = 'Classes/**/*'
  s.public_header_files = 'Classes/**/*.h'
  s.platform = :ios, '11.0'
  s.dependency 'Flutter'
  s.dependency 'Mantle'
  s.dependency 'SDWebImage'
  s.dependency 'SDWebImageWebPCoder'

	# Flutter.framework does not contain a i386 slice.
  s.pod_target_xcconfig = { 'DEFINES_MODULE' => 'YES', 'EXCLUDED_ARCHS[sdk=iphonesimulator*]' => 'i386' }
  s.swift_version = '5.0'
end

